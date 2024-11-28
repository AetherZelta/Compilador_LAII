/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectola2;

/**
 *
 * @author isaac
 */
public class Token {

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
}
