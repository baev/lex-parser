package ru.ifmo.sta.baev.parser;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.sta.baev.ParseException;
import ru.ifmo.sta.baev.Parser;

import java.io.InputStream;

import static java.lang.ClassLoader.getSystemResourceAsStream;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 02.04.13
 */
public class ParserMissSecondOperandTest {
    private static final String FILE_PATH = "ru/ifmo/sta/baev/parser/ps-miss-second-operand-test-data.txt";
    InputStream inputStream;

    @Before
    public void loadFile() {
        inputStream = getSystemResourceAsStream(FILE_PATH);
    }

    @Test(expected = ParseException.class)
    public void test() throws ParseException {
        Parser.parse(inputStream);
    }
}
