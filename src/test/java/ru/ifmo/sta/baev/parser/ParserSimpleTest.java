package ru.ifmo.sta.baev.parser;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.sta.baev.*;

import java.io.InputStream;

import static java.lang.ClassLoader.getSystemResourceAsStream;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 02.04.13
 */
public class ParserSimpleTest {
    private static final String SIMPLE_FILE_PATH = "ru/ifmo/sta/baev/parser/ps-simple-test-data.txt";
    InputStream inputStream;

    @Before
    public void loadFile() {
        inputStream = getSystemResourceAsStream(SIMPLE_FILE_PATH);
    }

    @Test
    public void simpleTest() throws ParseException {
        Parser.parse(inputStream);
    }
}
