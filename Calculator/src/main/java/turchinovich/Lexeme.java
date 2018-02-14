package turchinovich;

/**
 * Created by Mike on 05.02.2018.
 */

public class Lexeme
{
    private String lexemeText;
    private LexemeType type;

    public Lexeme(String lexemeText, LexemeType type)
    {
        this.lexemeText = lexemeText;
        this.type = type;
    }

    public String getLexemeText()
    {
        return lexemeText;
    }

    public LexemeType getType()
    {
        return type;
    }
}
