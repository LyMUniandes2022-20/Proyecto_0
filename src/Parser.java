import java.util.ArrayList;

public class Parser {
    /*
    reservedWords : are used how reserved words in our language, is an ArrayList cuz using "PROC" the user can add new reserved words.
    */
    private Lexer lexer;
    private ArrayList<String> commandsN = new ArrayList<>();
    private ArrayList<String> variablesCreated = new ArrayList<>();
    private boolean newVarWard = false;


    public Parser(){
        lexer = new Lexer();
        setCommandsN();
        setReservedWords();
    }

    private void setCommandsN(){
        //Here will be alll the commands as "XXXX(n)"
        commandsN.add("walk");
        commandsN.add("jump");
        commandsN.add("drop");
        commandsN.add("grab");
        commandsN.add("get");
        commandsN.add("free");
        commandsN.add("pop");
    }

    public ArrayList<String> getCommandsN(){
        return commandsN;
    }

    public ArrayList<String> getVariablesC(){
        return variablesCreated;
    }

    private ArrayList<String> reservedWords = new ArrayList<String>();
    public void setReservedWords() {
        reservedWords.add("var");
        reservedWords.add("PROC"); //Not yet
        reservedWords.add("CORP");
        reservedWords.add("jumpTo");
        reservedWords.add("veer");
        reservedWords.add("look");
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

    public ArrayList<String> getReservedWord(){
        return reservedWords;
    }

    // The parenthesis should to verify line to line
    private int openparenthesis = 0;
    private int closeparenthesis = 0;


    // The bracket should be verified at the final line
    private int openbrackets = 0;
    private int closebrackets = 0;

    // Verify if a proc has already open
    private boolean procWard = false; 

	public boolean verifier(ArrayList<String> tokens) {
        boolean okGrammar = true;
        String text = "";
		//This function will return true or false. 
		//True if the grammar its ok and viceversa

        //add the variables to the language
        if(tokens.get(0).equals("var")){
            for (int i = 1; i < tokens.size(); i++) {
                addVariables(tokens.get(i));
                i+=1;
            }
        }

        //add the name of the parameters to the language
        if(tokens.get(0).equals("PROC")){
            for (int i = 3; i < tokens.size(); i++) {
                
                if(lexer.getSeparator().contains(tokens.get(i))){
                    break;
                } else {
                    addVariables(tokens.get(i));
                    i+=1;
                }
            }
        }
        //Iteration of the array to verify if all tokens are in the lenguage
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

    public boolean reservedWordsVerifier(String token){
        /* Verify if all words are in the list of reservedWords
        i starts in 1 and ends in size()-1 to avoid PROG and GORP
        It will receive tokens as "PROC, GONORTH, (, )"
        IMPORTANT -> Could be necessary use split() for spacings in token*/
        boolean okGrammar = true;

        if (getReservedWord().contains(token) ||  lexer.getSeparator().contains(token) || getVariablesC().contains(token)) {
            okGrammar = true;

        } else {
            okGrammar = false;
        }
        return okGrammar;
    }

    public void addVariables(String token){
        variablesCreated.add(token);
    }
    //[walk,(,n,)]
    public boolean commandsNVerifier(ArrayList<String> tokens){
        boolean okGrammar = true;
        for (String token : tokens) {
            if (getCommandsN().contains(token)) {
                
            }
        }

        return okGrammar;
    }
}
