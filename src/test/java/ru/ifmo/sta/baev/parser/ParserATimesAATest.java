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
public class ParserATimesAATest {
    private static final String FILE_PATH = "ru/ifmo/sta/baev/parser/ps-a-times-aa-test-data.txt";
    InputStream inputStream;

    Tree tree;

    @Before
    public void loadFile() throws ParseException {
        inputStream = getSystemResourceAsStream(FILE_PATH);
        tree = Parser.parse(inputStream);
    }

    @Test
    public void aTimesAAOperatorTest() {
        assertThat(tree.getToken(), is(Token.MULTIPLICATION));
    }

    @Test
    public void aTimesAALeftOperandTest() {
        Tree left = tree.getLeft();
        assertThat(left.getToken(), is(Token.OPERAND));
        assertThat(left.getValue(), is(9));
    }

    @Test
    public void aTimesAARightOperandTest() {
        Tree right = tree.getRight();
        assertThat(right.getToken(), is(Token.OPERAND));
        assertThat(right.getValue(), is(123));
    }
}
