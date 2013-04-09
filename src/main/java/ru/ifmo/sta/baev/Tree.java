package ru.ifmo.sta.baev;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 01.04.13
 */
public class Tree {
    private Token token;
    private Integer value = null;
    private Tree left = null;
    private Tree right = null;

    public Tree() {
        this.token = Token.END;
    }

    public Tree(Integer value) {
        this.token = Token.OPERAND;
        this.value = value;
    }

    public Tree(Token token, Tree left, Tree right) {
        this.left = left;
        this.right = right;
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public Integer getValue() {
        return value;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    @Override
    public String toString() {
        switch (token) {
            case END:
                return "$";
            case OPERAND:
                return value.toString();
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case MULTIPLICATION:
                return "*";
        }
        return "null";
    }
}
