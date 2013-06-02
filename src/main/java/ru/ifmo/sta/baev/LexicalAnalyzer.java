package ru.ifmo.sta.baev;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 01.04.13
 */
public class LexicalAnalyzer {
    private InputStream is;
    private Token curentToken;
    private int position;
    private int character;
    private int operand;

    private static final char[] blankCharacters = {' ', '\n', '\t', '\r'};

    public LexicalAnalyzer(InputStream is) throws ParseException {
        this.is = is;
        this.position = 0;
        this.operand = 0;
        nextChar();
    }

    public boolean isBlank() {
        for (char blankCharacter : blankCharacters) {
            if (blankCharacter == character) {
                return true;
            }
        }
        return false;
    }

    private boolean isOperand() {
        return character >= '0' && character <= '9';
    }

    private void nextChar() throws ParseException {
        if (curentToken == Token.END) {
            return;
        }

        position++;

        try {
            character = is.read();
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), position);
        }
    }

    public void nextTaken() throws ParseException {
        while (isBlank()) {
            nextChar();
        }

        if (isOperand()) {
            operand = 0;
            while (isOperand()) {
                if (operand > Integer.MAX_VALUE / 10) {
                    throw new ParseException("Too mach value of operand: "
                            + Integer.toString(operand) + (character - '0') + "...", position);
                }
                operand = operand * 10 + (character - '0');
                nextChar();
            }
            curentToken = Token.OPERAND;
            return;
        }

        switch (character) {
            case '+':
                curentToken = Token.PLUS;
                break;
            case '-':
                curentToken = Token.MINUS;
                break;
            case '*':
                curentToken = Token.MULTIPLICATION;
                break;
            case -1:
                curentToken = Token.END;
                break;
            default:
                throw new ParseException("Illegal character \"" + (char) character + "\"", position);
        }
        nextChar();
    }

    public Token getCurentToken() {
        return curentToken;
    }

    public int getPosition() {
        return position;
    }

    public int getOperand() {
        return operand;
    }

    public boolean hasNextToken() {
        return curentToken != Token.END;
    }
}
