/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectola2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author isaac
 */
public class SyntaxAnalyzer {

    private List<Token> tokens;
    private int currentTokenIndex;
    private Token currentToken;
    private List<SyntaxError> errors;
    private static Map<String, SymbolTableEntry> symbolTable;
    private boolean analysisComplete;

    public SyntaxAnalyzer() {
        this.errors = new ArrayList<>();
        this.symbolTable = new HashMap<>();
        this.analysisComplete = false;
        /*if (symbolTable == null) {
            symbolTable = new HashMap<>();
        }
        this.analysisComplete = false;*/
    }

    public static Map<String, SymbolTableEntry> getSymbolTable() {
        if (symbolTable == null) {
            symbolTable = new HashMap<>();
        }
        return symbolTable;
    }

    public boolean isAnalysisComplete() {
        return analysisComplete;
    }

    public List<SyntaxError> analyze(List<Token> inputTokens) {
        this.tokens = new ArrayList<>(inputTokens); // Crear una copia de los tokens
        this.currentTokenIndex = 0;
        this.errors.clear();
        symbolTable.clear();
        this.analysisComplete = false;

        if (!tokens.isEmpty()) {
            currentToken = tokens.get(0);
            try {
                program();
                this.analysisComplete = true;
            } catch (Exception e) {
                errors.add(new SyntaxError(2999, "Error inesperado en el análisis: " + e.getMessage(),
                        currentToken.getLine(), currentToken.getColumn()));
            }
        }

        return errors;
    }

    private void reportError(String code, String message) {
        errors.add(new SyntaxError(Integer.parseInt(code), message,
                currentToken.getLine(), currentToken.getColumn()));
    }

    // Manejo de errores y recuperación
    private void reportError(String expected, Token found) {
        String message = String.format("Se esperaba '%s' pero se encontró '%s'",
                expected, found.getLexeme());
        errors.add(new SyntaxError(2000, message, found.getLine(), found.getColumn()));
    }

    private boolean match(int expectedType) {
        if (currentToken.getType() == expectedType) {
            advance();
            return true;
        }
        return false;
    }

    private void advance() {
        currentTokenIndex++;
        if (currentTokenIndex < tokens.size()) {
            currentToken = tokens.get(currentTokenIndex);
        }
    }

    private void recover(int... synchronizationTokens) {
        while (currentTokenIndex < tokens.size()) {
            for (int token : synchronizationTokens) {
                if (currentToken.getType() == token) {
                    return;
                }
            }
            advance();
        }
    }

    // Implementación de las reglas gramaticales
    private void program() {
        statementList();
    }

    private void statementList() {
        while (currentTokenIndex < tokens.size()
                && isStatementStart(currentToken.getType())) {
            statement();

            if (currentToken.getType() != 303) { // 303 es '}'
                if (!match(304)) { // Punto y coma
                    reportError(";", currentToken);
                    recover(304);
                }
            }
        }
    }

    private boolean isStatementStart(int type) {
        // Verificar si el token actual puede iniciar una declaración
        return type == 100
                || // int
                type == 101
                || // float
                type == 102
                || // boolean
                type == 103
                || // string
                type == 400
                || // identificador
                type == 104
                || // if
                type == 106
                || // while
                type == 107;   // print
    }

    private void statement() {
        switch (currentToken.getType()) {
            case 100: // int
            case 101: // float
            case 102: // boolean
            case 103: // string
                declaration();
                break;
            case 400: // identificador
                assignment();
                break;
            case 104: // if
                ifStatement();
                break;
            case 106: // while
                whileStatement();
                break;
            case 107: // print
                printStatement();
                break;
            default:
                reportError("declaración, asignación, if, while o print", currentToken);
                recover(304); // Recuperar hasta punto y coma
        }
    }

    private void declaration() {
        int type = currentToken.getType();
        advance();

        if (currentToken.getType() == 400) { // identificador
            String idName = currentToken.getLexeme();

            // Verificar si ya existe en la tabla de símbolos
            if (symbolTable.containsKey(idName)) {
                reportError("2001", "Variable ya declarada: " + idName);
            } else {
                // Agregar a la tabla de símbolos
                symbolTable.put(idName, new SymbolTableEntry(idName, type));
            }
            advance();
        } else {
            reportError("identificador", currentToken);
        }
    }

    private void assignment() {
        String idName = currentToken.getLexeme();

        // Verificar si la variable existe
        if (!symbolTable.containsKey(idName)) {
            reportError("2002", "Variable no declarada: " + idName);
        }

        advance();

        if (!match(220)) { // =
            reportError("=", currentToken);
            recover(304);
            return;
        }

        expression();
    }

    private void expression() {
        logicalExpression();
    }

    private void logicalExpression() {
        comparisonExpression();

        while (currentToken.getType() == 250
                || // &&
                currentToken.getType() == 251) {  // ||
            advance();
            comparisonExpression();
        }
    }

    private void comparisonExpression() {
        arithmeticExpression();

        if (isComparisonOperator(currentToken.getType())) {
            advance();
            arithmeticExpression();
        }
    }

    private boolean isComparisonOperator(int type) {
        return type >= 230 && type <= 235; // <, >, <=, >=, ==, !=
    }

    private void arithmeticExpression() {
        term();

        while (currentToken.getType() >= 200
                && currentToken.getType() <= 203) { // +, -, *, /
            advance();
            term();
        }
    }

    private String getTypeString(int type) {
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

    private void term() {
        if (currentToken.getType() == 400) { // identificador
            if (!symbolTable.containsKey(currentToken.getLexeme())) {
                reportError("2002", "Variable no declarada: " + currentToken.getLexeme());
            }
            advance();
        } else if (isLiteral(currentToken.getType())) {
            advance();
        } else if (match(300)) { // (
            expression();
            if (!match(301)) { // )
                reportError(")", currentToken);
            }
        } else if (match(252)) { // !
            term();
        } else {
            reportError("identificador, literal, ( o !", currentToken);
        }
    }

    private boolean isLiteral(int type) {
        return type == 401
                || // INT_LITERAL
                type == 402
                || // FLOAT_LITERAL
                type == 403
                || // STRING_LITERAL
                type == 108
                || // true
                type == 109;    // false
    }

    private void ifStatement() {
        advance(); // Consumir 'if'

        if (!match(300)) { // (
            reportError("(", currentToken);
            recover(302);
            return;
        }

        expression();

        if (!match(301)) { // )
            reportError(")", currentToken);
            recover(302);
            return;
        }

        if (!match(302)) { // {
            reportError("{", currentToken);
            return;
        }

        statementList();

        if (!match(303)) { // }
            reportError("}", currentToken);
            return;
        }

        if (currentToken.getType() == 105) { // else
            advance();
            if (!match(302)) { // {
                reportError("{", currentToken);
                return;
            }

            statementList();

            if (!match(303)) { // }
                reportError("}", currentToken);
            }
        }
    }

    private void whileStatement() {
        advance(); // Consumir 'while'

        if (!match(300)) { // (
            reportError("(", currentToken);
            recover(302);
            return;
        }

        expression();

        if (!match(301)) { // )
            reportError(")", currentToken);
            recover(302);
            return;
        }

        if (!match(302)) { // {
            reportError("{", currentToken);
            return;
        }

        statementList();

        if (!match(303)) { // }
            reportError("}", currentToken);
        }
    }

    private void printStatement() {
        advance(); // Consumir 'print'

        if (!match(300)) { // (
            reportError("(", currentToken);
            recover(301);
            return;
        }

        expression();

        if (!match(301)) { // )
            reportError(")", currentToken);
        }
    }

    public List<SyntaxError> getErrors() {
        return errors;
    }

    public String getAnalysisResults() {
        StringBuilder result = new StringBuilder();

        if (errors.isEmpty()) {
            result.append("Análisis sintáctico completado exitosamente.\n");
        } else {
            result.append("Se encontraron los siguientes errores sintácticos:\n");
            for (SyntaxError error : errors) {
                result.append(error.toString()).append("\n");
            }
        }

        result.append("\nEstado de la tabla de símbolos:\n");
        symbolTable.forEach((key, value) -> {
            result.append(String.format("Variable: %s, Tipo: %s\n",
                    key, getTypeString(value.getType())));
        });

        return result.toString();
    }
}

class SyntaxError {

    private final int code;
    private final String message;
    private final int line;
    private final int column;

    public SyntaxError(int code, String message, int line, int column) {
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

class SymbolTableEntry {

    private final String name;
    private final int type;
    private Object value;

    public SymbolTableEntry(String name, int type) {
        this.name = name;
        this.type = type;
        this.value = null;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
