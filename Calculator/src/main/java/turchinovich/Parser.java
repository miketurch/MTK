package turchinovich;

import java.io.EOFException;
import java.io.IOException;

/**
 * Created by Mike on 05.02.2018.
 */
public class Parser
{
    private Lexer lexer;
    private Lexeme currentLexeme;

    public Parser(Lexer lexer) throws IOException
    {
        this.lexer = lexer;
        this.currentLexeme = lexer.getLexeme();
    }

    public int calculate() throws IOException
    {
        int result = parseExpression();
        if(currentLexeme.getType() == LexemeType.EOF)
        {
            System.out.println(result);
            return result;
        }
        else
        {
            throw new ArithmeticException();
        }
    }

    private int parseExpression() throws IOException
    {
        int sign = 1;
        if(currentLexeme.getType() == LexemeType.PLUS || currentLexeme.getType() == LexemeType.MINUS)
        {
            sign = currentLexeme.getType() == LexemeType.MINUS ? -1 : 1;
            currentLexeme = lexer.getLexeme();
        }
        int result = parseTerm();
        result *= sign;
        while(currentLexeme.getType() == LexemeType.PLUS || currentLexeme.getType() == LexemeType.MINUS)
        {
            sign = currentLexeme.getType() == LexemeType.PLUS ? 1 : -1;
            currentLexeme = lexer.getLexeme();
            result += sign*parseTerm();
        }
        return result;
    }

    private int parseTerm() throws IOException
    {
        int result = parseFactor();
        while(currentLexeme.getType() == LexemeType.MULTIPLICATION || currentLexeme.getType() == LexemeType.DIVISION)
        {
            if(currentLexeme.getType() == LexemeType.MULTIPLICATION)
            {
                currentLexeme = lexer.getLexeme();
                result *= parseFactor();
            }
            else
            {
                currentLexeme = lexer.getLexeme();
                result /= parseFactor();
            }
        }
        return result;
    }

    public int powInteger(int base, int power)
    {
        int tmpBase = base;
        for(int i=0; i < power - 1; i++)
        {
            base *= tmpBase;
        }
        return base;
    }

    private int parseFactor() throws IOException
    {
        //int result = parsePower();
        int result = parseAtom();

        if(currentLexeme.getType() == LexemeType.POWER)
        {
            currentLexeme = lexer.getLexeme();
            result = powInteger(result, parseFactor());
        }
        return result;
    }

/*    private int parsePower() throws IOException
    {
        int sign = currentLexeme.getType() == LexemeType.MINUS ? -1 : 1;
        if(sign == -1)
        {
            currentLexeme = lexer.getLexeme();
        }
        return sign * parseAtom();
    }*/

    private int parseAtom() throws IOException
    {
        if(currentLexeme.getType() == LexemeType.OPEN_BRACKET)
        {
            currentLexeme = lexer.getLexeme();
            int result = parseExpression();
            if(currentLexeme.getType() == LexemeType.EOF)
            {
                throw new EOFException();
            }
            currentLexeme = lexer.getLexeme();
            return result;
        }
        try
        {
            int currentValue = Integer.parseInt(currentLexeme.getLexemeText());
            currentLexeme = lexer.getLexeme();
            return currentValue;
        }
        catch (NumberFormatException ex)
        {
            throw new NumberFormatException("Invalid integer");
        }
    }


}
