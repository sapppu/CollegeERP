package com.sqlplayground.engine.lexer;

public enum TokenType {
    // Keywords
    SELECT, FROM, WHERE, AND, OR, NOT, INSERT, INTO, VALUES,
    CREATE, TABLE, DROP, DELETE, UPDATE, SET,
    ORDER, BY, ASC, DESC, LIMIT, OFFSET,
    INNER, LEFT, RIGHT, JOIN, ON, GROUP, HAVING,
    DISTINCT, AS, NULL, IS, IN, LIKE, BETWEEN,
    INTEGER, VARCHAR, BOOLEAN, DOUBLE, TEXT,
    TRUE, FALSE,

    // Identifiers & Literals
    IDENTIFIER, STRING_LITERAL, NUMBER_LITERAL,

    // Operators
    EQUALS, NOT_EQUALS, LT, GT, LTE, GTE,
    PLUS, MINUS, STAR, SLASH, PERCENT,

    // Punctuation
    LPAREN, RPAREN, COMMA, SEMICOLON, DOT,

    // Special
    EOF, UNKNOWN
}
