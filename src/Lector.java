import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Lector {


    /**
     * @param route route of .txt which has a program
     * @return queue that contains all of the lines of the program
     * Than method will be used if prog/gorp is always used
     */
    public String readprog(String route){
        String cadenaFinal = "";
        String temp;
        try{
            BufferedReader bf = new BufferedReader(new FileReader(route));
            while(!(temp = bf.readLine()).equals("GORP")){
                if (!temp.equals("PROG") && !temp.equals("")){
                    cadenaFinal = cadenaFinal + " " + temp;
                }
            }
        }catch(Exception e){
            //Nada
        }

        return cadenaFinal;
    }

    /**
     * This method will be send the chains to lexer.
     * @param chains the chains which has already read by the methos
     */
    public void sentToLexer(String chains, Lexer lex, Parser parser){

        lex.interpreter(chains, parser);


    }


    
}
