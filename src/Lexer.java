import java.util.ArrayList;

public class Lexer {

	public Lexer() {
		setReservedWords();
	}
	
    /*
    separators : are used for cut chains which can be a variable or reserved word in our program.
    */
    private String[] separators = {" ", "(" , ")", "=", "," , "{", "}"};

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
    
    public boolean verifier(String linetxt) {
    	
		return true;
    }
}
