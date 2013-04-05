package ru.ifmo.sta.baev;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 01.04.13
 */
public class Tree {
    Token token;
    Integer value = null;
    Tree first = null;
    Tree second = null;

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
}
