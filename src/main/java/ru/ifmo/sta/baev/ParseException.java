package ru.ifmo.sta.baev;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 01.04.13
 */
public class ParseException extends Exception {
    int position;
    String message;

    public ParseException(String message, int position) {
        this.position = position;
        this.message = message;

    }

    @Override
    public String getMessage() {
        return message + " \nPosition: " + position;
    }
}
