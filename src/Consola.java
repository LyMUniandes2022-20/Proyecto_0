import java.util.ArrayList;

public class Consola {

    private Lexer lex;

    public void setLex(Lexer lex) {
        this.lex = lex;
    }

    public static void main(String[] args) {
        Consola consola = new Consola();
        Lector reader = new Lector();
        Lexer lex = new Lexer();
        Parser parser = new Parser();
        consola.setLex(lex);
        reader.sentToLexer(reader.readprog("data\\program.txt"), lex, parser);



    }
    

}