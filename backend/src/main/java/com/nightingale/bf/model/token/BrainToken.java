package com.nightingale.bf.model.token;

public enum BrainToken implements LangSpecificToken {
    INCR(TokenType.INCR, "\\++"), DECR(TokenType.DECR, "\\-+"),
    IN(TokenType.IN, ","), OUT(TokenType.OUT, "\\."),
    RIGHT(TokenType.RIGHT, ">+"), LEFT(TokenType.LEFT, "<+"),
    START(TokenType.START, "\\["), END(TokenType.END, "\\]");

    private final TokenType type;
    private final String value;

    BrainToken(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public TokenType getType() {
        return type;
    }

    @Override
    public String getPattern() {
        return value;
    }
}