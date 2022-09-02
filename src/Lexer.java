import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Lexer {


  /**
   * Constructor
   */
	public Lexer() {
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
      separators.add(";");
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
    public void interpreter(Queue<String> strings){
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
            


            // first, we will be sure of  temp is not empty, because you can have " " before to start an code line
            if (temp != ""){  
              toSentToParser.add(temp);
              temp = "";

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


