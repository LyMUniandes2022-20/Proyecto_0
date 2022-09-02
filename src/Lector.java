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
    public Queue<String> readprog(String route){

        Queue<String> strings = new LinkedList<String>();
        /* QUEUE: 
        To add .add
        To get .poll
        To clean .clear
        To get without delete head of queue .peek
        */

    try {
        BufferedReader bf = new BufferedReader(new FileReader(route));
        String temp = "";
        while((temp = bf.readLine()) != "GORP"){
            if (temp != "PROG" && temp != ""){
                strings.add(temp);
            }   
        }
        return strings;

    }
    

    /**
     * This method will be send the chains to lexer.
     * @param chains the chains which has already read by the methos
     */
    public void sentToLexer(Queue<String> chains, Lexer lex){

        lex.interpreter(chains);


    }


    
}
