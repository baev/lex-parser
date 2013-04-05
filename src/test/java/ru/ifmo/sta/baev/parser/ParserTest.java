package ru.ifmo.sta.baev.parser;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.sta.baev.ParseException;
import ru.ifmo.sta.baev.Parser;
import ru.ifmo.sta.baev.Tree;

import java.io.InputStream;

import static java.lang.ClassLoader.getSystemResourceAsStream;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 02.04.13
 */
public class ParserTest {
    private static final String FILE_PATH = "ru/ifmo/sta/baev/parser/ps-test-data.txt";
    InputStream inputStream;

    @Before
    public void loadFile() {
        inputStream = getSystemResourceAsStream(FILE_PATH);
    }

    @Test
    public void test() throws ParseException {
        Tree tree = Parser.parse(inputStream);
    }
}
