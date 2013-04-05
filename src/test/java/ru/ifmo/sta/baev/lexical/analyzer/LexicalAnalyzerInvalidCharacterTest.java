package ru.ifmo.sta.baev.lexical.analyzer;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.sta.baev.LexicalAnalyzer;
import ru.ifmo.sta.baev.ParseException;

import java.io.InputStream;

import static java.lang.ClassLoader.getSystemResourceAsStream;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 02.04.13
 */
public class LexicalAnalyzerInvalidCharacterTest {
    private static final String FILE_PATH = "ru/ifmo/sta/baev/lexical/analyzer/la-invalid-character-test-data.txt";
    InputStream inputStream;

    @Before
    public void loadFile() {
        inputStream = getSystemResourceAsStream(FILE_PATH);
    }

    @Test(expected = ParseException.class)
    public void bigintTest() throws ParseException {
        LexicalAnalyzer analyzer = new LexicalAnalyzer(inputStream);
        while (analyzer.hasNextToken()) {
            analyzer.nextTaken();
        }
    }
}
