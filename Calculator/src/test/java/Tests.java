
import turchinovich.Lexer;
import turchinovich.Parser;

import java.io.EOFException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mike on 05.02.2018.
 */
public class Tests
{
    @org.junit.Test
    public void plus() throws Exception
    {
        StringReader reader = new StringReader("3+2+5+7");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(17,parser.calculate());
    }

    @org.junit.Test
    public void minus() throws Exception
    {
        StringReader reader = new StringReader("3-2-5-7");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(-11,parser.calculate());
    }

    @org.junit.Test
    public void plusAndMinus() throws Exception
    {
        StringReader reader = new StringReader("3+2-5+7");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(7,parser.calculate());
    }

    @org.junit.Test
    public void multiplication() throws Exception
    {
        StringReader reader = new StringReader("3*2*4");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(24,parser.calculate());
    }

    @org.junit.Test
    public void division() throws Exception
    {
        StringReader reader = new StringReader("4/2");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(2,parser.calculate());
    }

    @org.junit.Test
    public void bracket() throws Exception
    {
        StringReader reader = new StringReader("3+(2+4)");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(9,parser.calculate());
    }

    @org.junit.Test
    public void power() throws Exception
    {
        StringReader reader = new StringReader("3*2^3");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(24,parser.calculate());
    }

    @org.junit.Test
    public void unaryMinus() throws Exception
    {
        StringReader reader = new StringReader("-3+4");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(1,parser.calculate());
    }

    @org.junit.Test
    public void unaryMinusAndPower() throws Exception
    {
        StringReader reader = new StringReader("-2^2");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(-4,parser.calculate());
    }

    @org.junit.Test
    public void unaryMinusInBracketAndPower() throws Exception
    {
        StringReader reader = new StringReader("(-2)^2");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(4,parser.calculate());
    }

    @org.junit.Test
    public void freeSpace() throws Exception
    {
        StringReader reader = new StringReader("3  +  4");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(7,parser.calculate());
    }

    @org.junit.Test
    public void mix1() throws Exception
    {
        StringReader reader = new StringReader("3+(2+4*5)/2");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(14,parser.calculate());
    }

    @org.junit.Test
    public void mix2() throws Exception
    {
        StringReader reader = new StringReader("3+(2+4*5)/2*5");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(58,parser.calculate());
    }

    @org.junit.Test
    public void mix3() throws Exception
    {
        StringReader reader = new StringReader("2^(3-1)*5");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(20,parser.calculate());
    }

    @org.junit.Test
    public void mix4() throws Exception
    {
        StringReader reader = new StringReader("3+(-5+2)");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        assertEquals(0,parser.calculate());
    }

    @org.junit.Test
    public void missNumber() throws Exception
    {
        StringReader reader = new StringReader("3+");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        boolean correct = false;
        try
        {
            parser.calculate();
        }
        catch (NumberFormatException ex)
        {
            correct = true;
        }
        assertEquals(true, correct);
    }

    @org.junit.Test
    public void checkEOF() throws Exception
    {
        StringReader reader = new StringReader("(2+3");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        boolean correct = false;
        try
        {
            parser.calculate();
        }
        catch (EOFException ex)
        {
            correct = true;
        }
        assertEquals(true, correct);
    }

    @org.junit.Test
    public void wrongBracket() throws Exception
    {
        StringReader reader = new StringReader("3+4)");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        boolean correct = false;
        try
        {
            parser.calculate();
        }
        catch (ArithmeticException ex)
        {
            correct = true;
        }
        assertEquals(true, correct);
    }

}
