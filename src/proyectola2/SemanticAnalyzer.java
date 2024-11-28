/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectola2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author isaac
 */
public class SemanticAnalyzer {

    private Map<String, SymbolTableEntry> symbolTable;
    private List<SemanticError> errors;
    private List<Token> tokens;

    public SemanticAnalyzer() {
        
        this.symbolTable = new HashMap<>();
        this.errors = new ArrayList<>();
    }

    public List<SemanticError> analyze(List<Token> inputTokens) {
        
        this.tokens = new ArrayList<>(inputTokens);
        this.errors.clear();
        SyntaxAnalyzer.getSymbolTable().clear();

        this.symbolTable = SyntaxAnalyzer.getSymbolTable();

        
        checkVariableDeclarations();
        checkTypeCompatibility();
        checkVariableUsage();

        return errors;
    }

    private void checkVariableDeclarations() {
        Set<String> declaredVariables = new HashSet<>();

        for (int i = 0; i < tokens.size(); i++) {
            if (isTypeDeclaration(tokens.get(i).getType())) {
                if (i + 1 < tokens.size() && tokens.get(i + 1).getType() == 400) {
                    String varName = tokens.get(i + 1).getLexeme();

                    if (declaredVariables.contains(varName)) {
                        addError(3001,
                                "Variable ya declarada: " + varName,
                                tokens.get(i + 1).getLine(),
                                tokens.get(i + 1).getColumn());
                        continue;
                    }

                    SymbolTableEntry existingEntry = symbolTable.get(varName);
                    if (existingEntry != null) {
                        addError(3001,
                                "Variable ya declarada previamente: " + varName,
                                tokens.get(i + 1).getLine(),
                                tokens.get(i + 1).getColumn());
                        continue;
                    }

                    int varType = getTypeFromTokenType(tokens.get(i).getType());
                    SymbolTableEntry newEntry = new SymbolTableEntry(varName, varType, true);
                    symbolTable.put(varName, newEntry);
                    declaredVariables.add(varName);
                }
            }

            if (tokens.get(i).getType() == 220) { // '=' token
                if (i > 0 && i + 1 < tokens.size()) {
                    Token varToken = tokens.get(i - 1);
                    Token valueToken = tokens.get(i + 1);

                    if (varToken.getType() == 400) { // Identifier
                        SymbolTableEntry entry = symbolTable.get(varToken.getLexeme());
                        if (entry == null) {
                            addError(3003,
                                    "Variable no declarada antes de su uso: " + varToken.getLexeme(),
                                    varToken.getLine(),
                                    varToken.getColumn());
                            continue;
                        }

                        Object value = extractTokenValue(valueToken);

                        if (value != null) {
                            entry.setValue(value);
                        }
                    }
                }
            }
        }
    }

    private int getTypeFromTokenType(int tokenType) {
        switch (tokenType) {
            case 100: // int keyword
            case 401:  // int literal
                return 100;
            case 101: // float keyword
            case 402:  // float literal
                return 101;
            case 102: // boolean keyword
            case 108:  // true
            case 109:  // false
                return 102;
            case 103: // string keyword
            case 403:  // string literal
                return 103;
            default:
                throw new IllegalArgumentException("Unsupported token type: " + tokenType);
        }
    }

    private Object extractTokenValue(Token token) {
        switch (token.getType()) {
            case 401: // INT_LITERAL
                return Integer.parseInt(token.getLexeme());
            case 402: // FLOAT_LITERAL
                return Float.parseFloat(token.getLexeme());
            case 403: // STRING_LITERAL
                return token.getLexeme().replace("\"", ""); // Remove quotes
            case 108: // true
                return true;
            case 109: // false
                return false;
            case 400: // Identifier
                SymbolTableEntry existingEntry = symbolTable.get(token.getLexeme());
                return existingEntry != null ? existingEntry.getCurrentValue() : null;
            default:
                return null;
        }
    }

    private void checkTypeCompatibility() {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getType() == 220) { // '=' token
                if (i > 0 && i + 1 < tokens.size()) {
                    Token varToken = tokens.get(i - 1);
                    Token valueToken = tokens.get(i + 1);

                    if (varToken.getType() == 400) { // Identifier
                        SymbolTableEntry entry = symbolTable.get(varToken.getLexeme());
                        if (entry != null) {
                            int expectedType = entry.getType();
                            int actualType = getTokenType(valueToken);

                            if (!isTypeCompatible(expectedType, actualType)) {
                                addError(3002,
                                        String.format("No se puede asignar %s a %s",
                                                getTypeName(actualType), getTypeName(expectedType)),
                                        valueToken.getLine(),
                                        valueToken.getColumn());
                            }
                        }
                    }
                }
            }
        }
    }

    private void checkTypeMatch(int expectedType, Token valueToken, Token errorToken) {
        int actualType = getTokenType(valueToken);

        if (!isTypeCompatible(expectedType, actualType)) {
            String errorMessage = generateTypeErrorMessage(expectedType, actualType);
            addError(3002,
                    errorMessage,
                    errorToken.getLine(),
                    errorToken.getColumn());
        }
    }

    private String generateTypeErrorMessage(int expectedType, int actualType) {
        String expectedTypeName = getTypeName(expectedType);
        String actualTypeName = getTypeName(actualType);

        switch (expectedType) {
            case 100: // int
                return String.format("Se requiere un valor entero, se encontró: %s", actualTypeName);
            case 101: // float
                return String.format("Se requiere un valor flotante, se encontró: %s", actualTypeName);
            case 102: // boolean
                return String.format("Se requiere un valor booleano (true/false), se encontró: %s", actualTypeName);
            case 103: // string
                return String.format("Se requiere un valor de cadena, se encontró: %s", actualTypeName);
            default:
                return String.format("Tipos incompatibles: no se puede asignar %s a %s",
                        actualTypeName, expectedTypeName);
        }
    }

    private void checkVariableUsage() {
        for (Token token : tokens) {
            if (token.getType() == 400) {
                SymbolTableEntry entry = symbolTable.get(token.getLexeme());

                if (entry == null) {
                    addError(3003,
                            "Variable no declarada: " + token.getLexeme(),
                            token.getLine(),
                            token.getColumn());
                }
            }
        }
    }

    private void addError(int code, String message, int line, int column) {
        errors.add(new SemanticError(code, message, line, column));
    }

    public Map<String, SymbolTableEntry> getSymbolTable() {
        return symbolTable;
    }

    public String getAnalysisResults() {
        StringBuilder result = new StringBuilder();

        if (errors.isEmpty()) {
            result.append("Análisis semántico completado exitosamente.\n");
        } else {
            result.append("Se encontraron los siguientes errores semánticos:\n");
            for (SemanticError error : errors) {
                result.append(error.toString()).append("\n");
            }
        }

        result.append("\nEstado de la tabla de símbolos:\n");
        symbolTable.forEach((key, value) -> {
            result.append(String.format(
                    "Variable: %s, Tipo: %s, Valor actual: %s\n",
                    key,
                    getTypeName(value.getType()),
                    value.getCurrentValue()
            ));
        });

        return result.toString();
    }

    // Métodos auxiliares para tipo y conversión
    private boolean isTypeDeclaration(int type) {
        return type >= 100 && type <= 103; // int, float, boolean, string
    }

    private int getTokenType(Token token) {
        switch (token.getType()) {
            case 401:
                return 100; // INT_LITERAL
            case 402:
                return 101; // FLOAT_LITERAL
            case 403:
                return 103; // STRING_LITERAL
            case 108:
                return 102; // true (BOOLEAN)
            case 109:
                return 102; // false (BOOLEAN)
            case 400: // Identifier
                SymbolTableEntry entry = symbolTable.get(token.getLexeme());
                return entry != null ? entry.getType() : -1;
            default:
                return -1;
        }
    }

    private boolean isTypeCompatible(int expectedType, int actualType) {
        if (expectedType == actualType) {
            return true;
        }

        switch (expectedType) {
            case 100: // int
                return isValidIntValue(actualType);
            case 101: // float
                // ONLY allow exact float types
                return actualType == 101 || actualType == 402;
            case 102: // boolean
                return isValidBooleanValue(actualType);
            case 103: // string
                return isValidStringValue(actualType);
            default:
                return false;
        }
    }

    private boolean isValidIntValue(int actualType) {
        return actualType == 100 || actualType == 401;
    }

    private boolean isValidFloatValue(int actualType) {
        return actualType == 100 || actualType == 101 || actualType == 402;
    }

    private boolean isValidBooleanValue(int actualType) {
        return actualType == 102
                || actualType == 108
                || // true token 
                actualType == 109;   // false token
    }

    private boolean isValidStringValue(int actualType) {
        return actualType == 103 || actualType == 403;
    }

    private String getTypeName(int type) {
        switch (type) {
            case 100:
                return "int";
            case 101:
                return "float";
            case 102:
                return "boolean";
            case 103:
                return "string";
            default:
                return "desconocido";
        }
    }
}

// Semantic Error class
class SemanticError {

    private final int code;
    private final String message;
    private final int line;
    private final int column;

    public SemanticError(int code, String message, int line, int column) {
        this.code = code;
        this.message = message;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("Error[%d] en línea %d, columna %d: %s",
                code, line, column, message);
    }

    // Getters
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
