package ru.ifmo.sta.baev.lexical.analyzer;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import ru.ifmo.sta.baev.LexicalAnalyzer;
import ru.ifmo.sta.baev.ParseException;
import ru.ifmo.sta.baev.Token;

import java.io.InputStream;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 02.04.13
 */
public class LexicalAnalyzerSimpleTest {
    private static final String SIMPLE_FILE_PATH = "ru/ifmo/sta/baev/lexical/analyzer/la-simple-test-data.txt";
    InputStream inputStream;

    @Before
    public void loadFile() {
        inputStream = getSystemResourceAsStream(SIMPLE_FILE_PATH);
    }

    @Test
    public void simpleTest() throws ParseException {
        LexicalAnalyzer analyzer = new LexicalAnalyzer(inputStream);
        analyzer.nextTaken();
        assertThat(analyzer.getCurentToken(), CoreMatchers.is(Token.PLUS));
        analyzer.nextTaken();
        assertThat(analyzer.getCurentToken(), is(Token.OPERAND));
        assertThat(analyzer.getOperand(), is(1));
        analyzer.nextTaken();
        assertThat(analyzer.getCurentToken(), is(Token.OPERAND));
        assertThat(analyzer.getOperand(), is(29));
    }
}
