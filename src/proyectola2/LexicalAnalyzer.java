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
public class LexicalAnalyzer {

    private String input;
    private int position;
    private int line;
    private int column;
    private List<Token> tokens;
    private Map<String, Integer> symbolTable;
    private List<LexicalError> errors;

    // Constructor
    public LexicalAnalyzer() {
        this.position = 0;
        this.line = 1;
        this.column = 1;
        this.tokens = new ArrayList<>();
        this.errors = new ArrayList<>();
        initializeSymbolTable();
    }

    // Inicializar tabla de símbolos con palabras reservadas
    private void initializeSymbolTable() {
        symbolTable = new HashMap<>();

        // Palabras reservadas
        symbolTable.put("int", 100);
        symbolTable.put("float", 101);
        symbolTable.put("boolean", 102);
        symbolTable.put("string", 103);
        symbolTable.put("if", 104);
        symbolTable.put("else", 105);
        symbolTable.put("while", 106);
        symbolTable.put("print", 107);
        symbolTable.put("true", 108);
        symbolTable.put("false", 109);
    }

    // Método principal de análisis
    public List<Token> analyze(String input) {
        this.input = input;
        this.position = 0;
        this.line = 1;
        this.column = 1;
        this.tokens.clear();
        this.errors.clear();

        while (position < input.length()) {
            char currentChar = input.charAt(position);

            if (currentChar == '/' && position + 1 < input.length() && input.charAt(position + 1) == '/') {
                analyzeSingleLineComment();
                continue;
            }

            // Check for multi-line comment
            if (currentChar == '/' && position + 1 < input.length() && input.charAt(position + 1) == '*') {
                analyzeMultiLineComment();
                continue;
            }

            // Check for invalid single-line comment attempt (excluding multi-line comments)
            if (currentChar == '/' && position + 1 < input.length()
                    && input.charAt(position + 1) != '/'
                    && input.charAt(position + 1) != '*') {
                reportError(1006, "Formato de comentario de línea inválido. Use '//' para comentarios de una línea");
                position++;
                column++;
                continue;
            }
            // Ignorar espacios en blanco y saltos de línea
            if (Character.isWhitespace(currentChar)) {
                if (currentChar == '\n') {
                    line++;
                    column = 1;
                } else {
                    column++;
                }
                position++;
                continue;
            }

            // Identificadores y palabras reservadas
            if (Character.isLetter(currentChar)) {
                analyzeIdentifier();
                continue;
            }

            // Números (enteros y decimales)
            if (Character.isDigit(currentChar)) {
                analyzeNumber();
                continue;
            }

            // Strings
            if (currentChar == '"') {
                analyzeString();
                continue;
            }

            // Operadores y símbolos especiales
            if (isOperatorOrSymbol(currentChar)) {
                analyzeOperator();
                continue;
            }

            // Carácter inválido
            reportError(1000, "Carácter inválido en la entrada: " + currentChar);
            position++;
            column++;
        }

        return tokens;
    }

    private void analyzeSingleLineComment() {
        // Skip the '//'
        position += 2;
        column += 2;

        // Continue until end of line or end of input
        while (position < input.length() && input.charAt(position) != '\n') {
            position++;
            column++;
        }

        // If ended by newline, update line and column
        if (position < input.length() && input.charAt(position) == '\n') {
            line++;
            column = 1;
            position++;
        }
    }

    private void analyzeMultiLineComment() {
        // Skip the '/*'
        position += 2;
        column += 2;

        // Track nested comment depth
        int depth = 1;

        while (position < input.length()) {
            if (position + 1 < input.length()) {
                // Check for nested comments
                if (input.charAt(position) == '/' && input.charAt(position + 1) == '*') {
                    depth++;
                    position += 2;
                    column += 2;
                    continue;
                }

                // Check for comment end
                if (input.charAt(position) == '*' && input.charAt(position + 1) == '/') {
                    depth--;
                    position += 2;
                    column += 2;

                    // If all nested comments are closed, exit
                    if (depth == 0) {
                        break;
                    }
                    continue;
                }
            }

            // Handle newlines within comment
            if (input.charAt(position) == '\n') {
                line++;
                column = 1;
            } else {
                column++;
            }
            position++;
        }

        // Check if comment was not closed
        if (depth > 0) {
            reportError(1005, "Comentario de múltiples líneas no cerrado");
        }
    }

    // Análisis de identificadores y palabras reservadas
    private void analyzeIdentifier() {
        StringBuilder lexeme = new StringBuilder();
        int startColumn = column;

        // Validar que el identificador comience con una letra o guión bajo
        if (!Character.isLetter(input.charAt(position)) && input.charAt(position) != '_') {
            reportError(1001, "Identificador debe comenzar con una letra o guión bajo: " + input.charAt(position));

            // Saltar el token inválido
            while (position < input.length()
                    && (Character.isLetterOrDigit(input.charAt(position)) || input.charAt(position) == '_')) {
                position++;
                column++;
            }
            return;
        }

        // Construir el identificador
        while (position < input.length()
                && (Character.isLetterOrDigit(input.charAt(position)) || input.charAt(position) == '_')) {
            lexeme.append(input.charAt(position));
            position++;
            column++;
        }

        String identifier = lexeme.toString();

        // Verificar el formato del identificador
        if (!identifier.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            reportError(1001, "Identificador desconocido o mal formado: " + identifier);
            return;
        }

        // Verificar si es palabra reservada
        if (symbolTable.containsKey(identifier)) {
            tokens.add(new Token(symbolTable.get(identifier), identifier, line, startColumn));
        } else {
            tokens.add(new Token(400, identifier, line, startColumn)); // Identificador válido
        }
    }

    // Análisis de números
    private void analyzeNumber() {
        StringBuilder lexeme = new StringBuilder();
        int startColumn = column;
        boolean isFloat = false;

        if (input.charAt(position) == '0' && position + 1 < input.length()
                && Character.isDigit(input.charAt(position + 1))) {
            reportError(1011, "Formato inválido de número: " + input.charAt(position) + input.charAt(position + 1));

            // Consumir el número inválido
            while (position < input.length() && Character.isDigit(input.charAt(position))) {
                position++;
                column++;
            }
            return;
        }

        while (position < input.length()) {
            char currentChar = input.charAt(position);

            if (Character.isDigit(currentChar)) {
                lexeme.append(currentChar);
                position++;
                column++;
            } else if (currentChar == '.' && !isFloat) {
                lexeme.append(currentChar);
                isFloat = true;
                position++;
                column++;
            } else if (Character.isLetter(currentChar) || currentChar == '_') {
                // Si hay una letra o guión bajo después del número, es un error
                reportError(1010, "Identificador inválido comienza con número: " + lexeme + currentChar);

                // Consumir el token inválido
                while (position < input.length()
                        && (Character.isLetterOrDigit(input.charAt(position)) || input.charAt(position) == '_')) {
                    position++;
                    column++;
                }
                return;
            } else {
                break;
            }
        }

        String number = lexeme.toString();

        // Validar formato del número
        if (isFloat) {
            if (number.endsWith(".")) {
                reportError(1010, "Formato inválido de número decimal: " + number);
                return;
            }
            tokens.add(new Token(402, number, line, startColumn)); // FLOAT_LITERAL
        } else {
            tokens.add(new Token(401, number, line, startColumn)); // INT_LITERAL
        }
    }

    // Análisis de strings
    private void analyzeString() {
        StringBuilder lexeme = new StringBuilder();
        int startColumn = column;
        position++; // Saltar la comilla inicial
        column++;

        while (position < input.length() && input.charAt(position) != '"') {
            // Manejar secuencias de escape
            if (input.charAt(position) == '\\') {
                if (position + 1 < input.length()) {
                    char nextChar = input.charAt(position + 1);
                    if (isValidEscapeSequence(nextChar)) {
                        lexeme.append('\\').append(nextChar);
                        position += 2;
                        column += 2;
                        continue;
                    }
                }
                reportError(1004, "Secuencia de escape inválida");
            }

            lexeme.append(input.charAt(position));
            position++;
            column++;
        }

        if (position >= input.length()) {
            reportError(1003, "Cadena no terminada: falta comilla de cierre");
            return;
        }

        position++; // Saltar la comilla final
        column++;
        tokens.add(new Token(403, lexeme.toString(), line, startColumn)); // STRING_LITERAL
    }

    // Análisis de operadores y símbolos
    private void analyzeOperator() {
        char currentChar = input.charAt(position);
        int startColumn = column;
        StringBuilder operator = new StringBuilder().append(currentChar);

        // Operadores de dos caracteres
        if (position + 1 < input.length()) {
            String possibleOp = "" + currentChar + input.charAt(position + 1);
            if (isDoubleOperator(possibleOp)) {
                operator.append(input.charAt(position + 1));
                position += 2;
                column += 2;
                tokens.add(new Token(getOperatorCode(operator.toString()),
                        operator.toString(), line, startColumn));
                return;
            }
        }

        // Operadores de un carácter
        position++;
        column++;
        tokens.add(new Token(getOperatorCode(operator.toString()),
                operator.toString(), line, startColumn));
    }

    // Métodos auxiliares
    private boolean isOperatorOrSymbol(char c) {
        return "+-*/<>=!&|;(){}\"".indexOf(c) != -1;
    }

    private boolean isDoubleOperator(String op) {
        return "==".equals(op) || "!=".equals(op) || "<=".equals(op)
                || ">=".equals(op) || "&&".equals(op) || "||".equals(op);
    }

    private boolean isValidEscapeSequence(char c) {
        return "\"\\nrt".indexOf(c) != -1;
    }

    private int getOperatorCode(String operator) {
        switch (operator) {
            case "+":
                return 200;
            case "-":
                return 201;
            case "*":
                return 202;
            case "/":
                return 203;
            case "=":
                return 220;
            case "<":
                return 230;
            case ">":
                return 231;
            case "<=":
                return 232;
            case ">=":
                return 233;
            case "==":
                return 234;
            case "!=":
                return 235;
            case "&&":
                return 250;
            case "||":
                return 251;
            case "!":
                return 252;
            case "(":
                return 300;
            case ")":
                return 301;
            case "{":
                return 302;
            case "}":
                return 303;
            case ";":
                return 304;
            default:
                return -1;
        }
    }

    private void reportError(int code, String message) {
        errors.add(new LexicalError(code, message, line, column));
    }

    // Getter para errores
    public List<LexicalError> getErrors() {
        return errors;
    }
}

// Clases auxiliares
/*class Token {

    private final int type;
    private final String lexeme;
    private final int line;
    private final int column;

    public Token(int type, String lexeme, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    // Getters
    public int getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return String.format("Token{type=%d, lexeme='%s', line=%d, column=%d}",
                type, lexeme, line, column);
    }
}*/

class LexicalError {

    private final int code;
    private final String message;
    private final int line;
    private final int column;

    public LexicalError(int code, String message, int line, int column) {
        this.code = code;
        this.message = message;
        this.line = line;
        this.column = column;
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

    @Override
    public String toString() {
        return String.format("Error[%d] en línea %d, columna %d: %s",
                code, line, column, message);
    }
}
