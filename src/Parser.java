import java.util.ArrayList;

public class Parser {
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
        reservedWords.add("od");
        reservedWords.add("fi");
        reservedWords.add("per");
        reservedWords.add("repeatTimes");
    }

    // The parenthesis should to verify line to line
    private int openparenthesis = 0;
    private int closeparenthesis = 0;


    // The bracket should be verified at the final line
    private int openbrackets = 0;
    private int closebrackets = 0;

    // Verify if a proc has already open
    private boolean procWard = false; 

    public void verifyLine(ArrayList<String> tokens){

        
        

    }
	public boolean verifier(ArrayList<String> lineText, String[] tokens) {
        boolean okGrammar = true;
        String text = "";
        if(progVerifier(lineText) == false){
            okGrammar = false;
            return okGrammar;
        }

		//This function will return true or false. 
		//True if the grammar its ok and viceversa
		for (String token : tokens) {
            if(reservedWordsVerifier(token)){
                text += token+" ";
            }else{
                okGrammar = false;
                System.out.println("Failed token: "+token);
            }
        }
        System.out.println(text);
        return true;
	}
	
	public boolean progVerifier(ArrayList<String> lineText) {
		boolean condition = true;
		//First condition in order to know that the grammar its ok
		try {
            //Verify if starts with PROG
			if (lineText.get(0).equals("PROG") == false) {
				return false;
			}
            //Verify if ends with GORP
			if (lineText.get(lineText.size()-1).equals("GORP") == false) {
				System.out.println("Isn't ok");
				return false;
			}
		} catch (Exception e) {
			//TODO: handle exception
		}
		return condition;
	}

    public boolean reservedWordsVerifier(String token){
        /* Verify if all words are in the list of reservedWords
        i starts in 1 and ends in size()-1 to avoid PROG and GORP
        It will receive tokens as "PROC, GONORTH, (, )"
        IMPORTANT -> Could be necessary use split() for spacings in token*/
        if (lexer.getReservedWords().contains(token) ||  lexer.getSeparator().contains(token)) {
            return true;
        }
        return false;
    }
}
