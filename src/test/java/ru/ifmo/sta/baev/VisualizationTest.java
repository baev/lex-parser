package ru.ifmo.sta.baev;

import org.junit.Test;

import static java.lang.ClassLoader.getSystemResourceAsStream;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 06.04.13
 */
public class VisualizationTest {
    private static final String FILE_PATH = "ru/ifmo/sta/baev/visualization/vz-sample.txt";

    @Test
    public void test() throws ParseException {
        Tree treeToVisualization = Parser.parse(getSystemResourceAsStream(FILE_PATH));
    }
}
