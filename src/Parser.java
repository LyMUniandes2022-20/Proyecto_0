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
}


