package ru.ifmo.sta.baev.lexical.analyzer;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.sta.baev.LexicalAnalyzer;
import ru.ifmo.sta.baev.ParseException;

import java.io.InputStream;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 02.04.13
 */
public class LexicalAnalyzerTest {
    private static final String FILE_PATH = "ru/ifmo/sta/baev/lexical/analyzer/la-test-data.txt";
    InputStream inputStream;

    @Before
    public void loadFile() {
        inputStream = getSystemResourceAsStream(FILE_PATH);
    }

    @Test
    public void laTest() throws ParseException {
        LexicalAnalyzer analyzer = new LexicalAnalyzer(inputStream);

        int countPlus = 0;
        int countMinus = 0;
        int countMultiply = 0;
        int countOperands = 0;

        while (analyzer.hasNextToken()) {
            analyzer.nextTaken();

            switch (analyzer.getCurentToken()) {
                case PLUS:
                    countPlus++;
                    break;
                case MINUS:
                    countMinus++;
                    break;
                case MULTIPLICATION:
                    countMultiply++;
                    break;
                case OPERAND:
                    countOperands++;
                    break;
            }
        }

        assertThat(countPlus, is(5));
        assertThat(countMinus, is(5));
        assertThat(countMultiply, is(4));
        assertThat(countOperands, is(24));
    }
}
