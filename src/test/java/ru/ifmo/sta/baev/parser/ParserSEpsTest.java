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
public class ParserSEpsTest {
    private static final String FILE_PATH = "ru/ifmo/sta/baev/parser/ps-s-eps-test-data.txt";
    InputStream inputStream;

    @Before
    public void loadFile() {
        inputStream = getSystemResourceAsStream(FILE_PATH);
    }

    @Test
    public void sEpsTest() throws ParseException {
        Tree tree = Parser.parse(inputStream).getOperator();
        assertThat(tree.getToken(), is(Token.END));
    }
}
