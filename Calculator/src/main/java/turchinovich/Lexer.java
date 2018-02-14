package turchinovich;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Mike on 05.02.2018.
 */
public class Lexer
{
    private final static String EOF = "￿";
    Reader reader;
    String currentSymbol = "";

    public Lexer(Reader reader) throws IOException
    {
        this.reader = reader;
        currentSymbol += (char) reader.read();
    }

    public boolean isDigit(int intValueOfChar)
    {
        return (intValueOfChar >= '0') && (intValueOfChar <= '9');
    }

    public void readNextSymbol() throws IOException
    {
        currentSymbol = "";
        currentSymbol += (char) reader.read();
    }

    Lexeme getLexeme() throws IOException
    {
        switch (currentSymbol)
        {
            case "+":
            {
                readNextSymbol();
                return new Lexeme("+", LexemeType.PLUS);
            }
            case "-":
            {
                readNextSymbol();
                return new Lexeme("-", LexemeType.MINUS);
            }
            case "*":
            {
                readNextSymbol();
                return new Lexeme("*", LexemeType.MULTIPLICATION);
            }
            case "/":
            {
                readNextSymbol();
                return new Lexeme("/", LexemeType.DIVISION);
            }
            case "^":
            {
                readNextSymbol();
                return new Lexeme("^", LexemeType.POWER);
            }
            case "(":
            {
                readNextSymbol();
                return new Lexeme("(", LexemeType.OPEN_BRACKET);
            }
            case ")":
            {
                readNextSymbol();
                return new Lexeme(")", LexemeType.CLOSE_BRACKET);
            }
            case " ":
            {
                readNextSymbol();
                return getLexeme();
            }
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            {
                int intValueOfChar = reader.read();
                while(isDigit(intValueOfChar))
                {
                    currentSymbol += (char) intValueOfChar;
                    intValueOfChar = reader.read();
                }
                String currentLexeme = currentSymbol;
                currentSymbol = "";
                currentSymbol += (char) intValueOfChar;
                return new Lexeme(currentLexeme, LexemeType.NUMBER);
            }
            case EOF:
            {
                return new Lexeme("", LexemeType.EOF);
            }
            default:
            {
                return null;
            }

        }
    }
}
