import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Parser {
	//The function of the parser is to verify if everything its ok with the grammar
	private Lexer lexer;

	public Parser(){
		lexer = new Lexer();
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
