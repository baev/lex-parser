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
    private Tree operator = null;
    private String write;

    public Tree(Tree child) {
        this.operator = child;
        this.write = "S";
    }

    public Tree(Integer value) {
        this.token = Token.OPERAND;
        this.value = value;
        this.write = value.toString();
    }

    public Tree(Token token) {
        this.token = token;
        this.write = tokenToSting(token);
    }

    private String tokenToSting(Token token) {
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
        return null;
    }

    public Tree(Token token, Tree left, Tree right) {
        this.left = left;
        this.right = right;
        this.token = token;
        this.operator = new Tree(token);
        this.write = "A";
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

    public Tree getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return write;
    }
}
