package ru.ifmo.sta.baev;

import java.io.InputStream;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 05.04.13
 */
public class Parser {
    public static Tree parse(InputStream is) throws ParseException {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(is);

        lexicalAnalyzer.nextTaken();
        return S(lexicalAnalyzer);
    }

    private static Tree S(LexicalAnalyzer lexicalAnalyzer) throws ParseException {
        Token curToken = lexicalAnalyzer.getCurentToken();
        Tree result;

        switch (curToken) {
            case END:
                result =  new Tree(new Tree(Token.END));
                break;
            default:
                result = new Tree(A(lexicalAnalyzer));
        }

        lexicalAnalyzer.nextTaken();
        if (lexicalAnalyzer.hasNextToken()) {
            throw new ParseException("Expected END but has "
                    + lexicalAnalyzer.getCurentToken(), lexicalAnalyzer.getPosition());
        }

        return result;
    }

    private static Tree A(LexicalAnalyzer lexicalAnalyzer) throws ParseException {
        Token curToken = lexicalAnalyzer.getCurentToken();

        switch (curToken) {
            case END:
                throw new ParseException("Expected A but has none.", lexicalAnalyzer.getPosition());
            case OPERAND:
                return new Tree(lexicalAnalyzer.getOperand());

            default:
                if (!lexicalAnalyzer.hasNextToken()) {
                    throw new ParseException("Expected first OPERAND but has none.", lexicalAnalyzer.getPosition());
                }
                lexicalAnalyzer.nextTaken();
                Tree first = A(lexicalAnalyzer);

                if (!lexicalAnalyzer.hasNextToken()) {
                    throw new ParseException("Expected second OPERAND but has none.", lexicalAnalyzer.getPosition());
                }
                lexicalAnalyzer.nextTaken();
                Tree second = A(lexicalAnalyzer);

                return new Tree(curToken, first, second);
        }
    }
}
