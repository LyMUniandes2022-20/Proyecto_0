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

    public ArrayList<String> getSeparator(){
      return this.separators;
    }

    /*
     * 
     */
    public boolean interpreter(String string, Parser parser){
      boolean okGrammar = false;
      ArrayList<String> toSentToParser = new ArrayList<String>();

      // convert the chain into a char []
      char[] chaintoarray = string.toCharArray();
        

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
          if (!toCompare.equals(" ")){
            toSentToParser.add(toCompare);
            }            
          }
        }

        okGrammar = separator(toSentToParser,parser);
        return okGrammar;
      }


      private boolean separator(ArrayList<String> tokens, Parser parser){
        boolean parserAnswer = false;
        ArrayList<String> finalBlock = new ArrayList<String>();
        boolean isProc = false;
        int openBracket = 0;
        for (String token : tokens){
          token = token.trim();


          if (isProc == true){
              if (token.equals("CORP")){
                finalBlock.add(token);
                parserAnswer = parser.verifier(finalBlock);               
                if (parserAnswer == false) {
                  return parserAnswer;
                }
                finalBlock.clear();
                isProc = false;
              }else{
                finalBlock.add(token);
              }
          }else if (openBracket > 0){
              if (token.equals("}")){
                openBracket --;
                finalBlock.add(token);
                  if(openBracket == 0){
                    parserAnswer = parser.verifier(finalBlock);
                    if (parserAnswer == false) {
                      return parserAnswer;
                    }
                    finalBlock.clear();
                  }
              }else if(token.equals("{")){
                openBracket++;
                finalBlock.add(token);
              }else{
                finalBlock.add(token);
              }
          }else if (token.equals("PROC")){
              finalBlock.add(token);
              isProc = true;
          }else if (token.equals("{")){
              finalBlock.add(token);
              openBracket ++;
          }else if (token.equals(";")){
              finalBlock.add(token);
              parserAnswer = parser.verifier(finalBlock);
              if (parserAnswer == false) {
                return parserAnswer;
              }

              finalBlock.clear();
          }else{
              finalBlock.add(token);
          }

        }
        return parserAnswer;
      }

      // private void printTest(ArrayList<String> imprimir){
      //   System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
      //   for (String imprimirxd : imprimir){
      //     System.out.println(imprimirxd);
      //   }
      // }
    }
    


