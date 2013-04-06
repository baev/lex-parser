package ru.ifmo.sta.baev;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 01.04.13
 */
public class Tree {
    private Token token;
    private Integer value = null;
    private Tree first = null;
    private Tree second = null;

    public Tree() {
        this.token = Token.END;
    }

    public Tree(Integer value) {
        this.token = Token.OPERAND;
        this.value = value;
    }

    public Tree(Token token, Tree first, Tree second) {
        this.first = first;
        this.second = second;
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public Integer getValue() {
        return value;
    }

    public Tree getFirst() {
        return first;
    }

    public Tree getSecond() {
        return second;
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
