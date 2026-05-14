package com.sqlplayground.engine.lexer;

public class Token {
    public final TokenType type;
    public final String value;
    public final int pos;

    public Token(TokenType type, String value, int pos) {
        this.type = type;
        this.value = value;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return String.format("Token(%s, '%s', @%d)", type, value, pos);
    }
}
