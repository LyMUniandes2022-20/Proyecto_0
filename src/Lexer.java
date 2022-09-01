import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Lexer {


  /**
   * Constructor
   */
	public Lexer() {
		setReservedWords();
    setSeparators();
	}
	
    /*
    separators : are used for cut chains which can be a variable or reserved word in our program.
    */
    private ArrayList<String> separators = new ArrayList<String> ();
    public void setSeparators(){
      separators.add(" ");
      separators.add("(");
      separators.add(")");
      separators.add("=");
      separators.add(",");
      separators.add("{");
      separators.add("}");
    }


    /*
     strings: contains all chains which lector was reading
     */
    Queue<String> strings;

    /*
    reservedWords : are used how reserved words in our language, is an ArrayList cuz using "PROC" the user can add new reserved words.
    */
    private ArrayList<String> reservedWords = new ArrayList<String>();
    public void setReservedWords() {
        reservedWords.add("var");
        reservedWords.add("PROC"); //Not yet
        reservedWords.add("CORP");
        reservedWords.add("walk");
        reservedWords.add("jump");
        reservedWords.add("jumpTo");
        reservedWords.add("veer");
        reservedWords.add("look");
        reservedWords.add("drop");
        reservedWords.add("grab");
        reservedWords.add("get");
        reservedWords.add("free");
        reservedWords.add("pop");
        reservedWords.add("walk");
        reservedWords.add("if");
        reservedWords.add("else");
        reservedWords.add("while");
        reservedWords.add("do");
        reservedWords.add("isfacing");
        reservedWords.add("isValid");
        reservedWords.add("drop");
        reservedWords.add("canWalk");
        reservedWords.add("not");
    }
    
    public ArrayList<String> getReservedWords(){
      return this.reservedWords;
    }

    public ArrayList<String> getSeparator(){
      return this.separators;
    }

    /*
     * 
     */
    public void interpreter(){
      //all of strings in the queue will iterate
      for (int i = 1 ; i<= strings.size(); i++){

        // They are the array of tokens which we will sent to the parser
        ArrayList<String> toSentToParser = new ArrayList<String>();

        
        // get the head of the queue
        String chain = strings.poll();
        // convert the chain into a char []
        char[] chaintoarray = chain.toCharArray();
        
        //This temp will be contains an incompleted token, until that we found an separator
        String temp = "";

        // iterate the char[], one iteration is one character in the chain
        for (char character : chaintoarray){

          // convert char to string
          String toCompare = String.valueOf(character);

          // review if the character is an separator
          if (!separators.contains(toCompare)){
            // if is not a separator, the character will be concatenate with something
            temp = temp + toCompare;
          }else{
            // if the character is a separator, temp should be a token 
            


            // first, we will be sure of the temp is not empty, because you can have " " before to start an code line
            if (temp != ""){
              if (reservedWords.contains(temp)){
              toSentToParser.add(temp);
              temp = "";
             }else{
              // HERE WE WILL SENT AN ERROR CUZ TEMP IS NOT A RESERVED WORD
             }
            }

            //then, if the separator is not a " " , they must stay into the array
            if (toCompare != " "){
              toSentToParser.add(toCompare);
            }            
          }

        }

        // HERE THE ARRAY WILL BE SENT TO A PARSER
      }

    }
    }


