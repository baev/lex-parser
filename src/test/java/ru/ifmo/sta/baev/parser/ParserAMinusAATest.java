package ru.ifmo.sta.baev.parser;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.sta.baev.ParseException;
import ru.ifmo.sta.baev.Parser;
import ru.ifmo.sta.baev.Token;
import ru.ifmo.sta.baev.Tree;

import java.io.InputStream;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 02.04.13
 */
public class ParserAMinusAATest {
    private static final String FILE_PATH = "ru/ifmo/sta/baev/parser/ps-a-minus-aa-test-data.txt";
    InputStream inputStream;

    Tree tree;

    @Before
    public void loadFile() throws ParseException {
        inputStream = getSystemResourceAsStream(FILE_PATH);
        tree = Parser.parse(inputStream).getOperator();
    }

    @Test
    public void aMinusAAOperatorTest() {
        assertThat(tree.getToken(), is(Token.MINUS));
    }

    @Test
    public void aMinusAALeftOperandTest() {
        Tree left = tree.getLeft();
        assertThat(left.getToken(), is(Token.OPERAND));
        assertThat(left.getValue(), is(4311));
    }

    @Test
    public void aMinusAARightOperandTest() {
        Tree right = tree.getRight();
        assertThat(right.getToken(), is(Token.OPERAND));
        assertThat(right.getValue(), is(22));
    }
}
