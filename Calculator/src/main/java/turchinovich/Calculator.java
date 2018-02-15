package turchinovich;

import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Mike on 05.02.2018.
 */
public class Calculator
{
    public static void main(String[] args) throws IOException
    {
        StringReader reader = new StringReader("3+6*4+4/2");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        try
        {
            parser.calculate();
        }
        catch (NumberFormatException | NullPointerException | ArithmeticException | EOFException ex )
        {
            System.out.println("This is not an arithmetic expression");
        }

    }
}
