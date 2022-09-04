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
    private int openparentheses = 0;
    private int closeparentheses = 0;


    // The bracket should be verified at the final line
    private int openbrackets = 0;
    private int closebrackets = 0;

    // Verify if a proc has already open
    private boolean procWard = false; 

	public boolean verifier(ArrayList<String> tokens) {
        boolean okTokens = true;
        boolean okParentheses = true;
        String text = "";
		//This function will return true or false. 
		//True if the grammar its ok and viceversa

        //add the variables to the language
        if(tokens.get(0).equals("var")){

            boolean checkVar =  checkVarLine (tokens);
            return checkVar;

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
                okTokens = false;
                System.out.println("Failed token: "+token);
                break;
            }
        }
        okParentheses = checkParentheses();
        System.out.println(text);


        return okTokens;
	}

    public boolean reservedWordsVerifier(String token){
        /* Verify if all words are in the list of reservedWords
        i starts in 1 and ends in size()-1 to avoid PROG and GORP
        It will receive tokens as "PROC, GONORTH, (, )"
        IMPORTANT -> Could be necessary use split() for spacings in token*/
        boolean okGrammar = true;

        countParentheses(token);
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

    /**
     * @param line the arraylist of tokens of one line
     * @return return an boolean, true is if the line are syntactical correct, false is the contrary
     */
    private boolean checkVarLine(ArrayList<String> line){

        boolean isCorrect = true;

        // Isn't necesary to know the first token, that are "var"
        line.remove(0);

        // Verify if the last token of a line are ";"
        if (!line.get(line.size()-1).equals(";")){
            return false; 
        }else{
            // If the line containst that character, will be clear of the array because that would be an inconvenent 
            line.remove(line.size()-1);
        }

        // Verify if the unpair tokens of a line are ","
        for (int i = 1; i < line.size(); i++) {
            if (!line.get(i).equals(",")){
                return false;
            }
            i+=1;
        }

        //Add the new reserved tokens of the var definition
        for (int i = 0; i < line.size(); i++) {
            addVariables(line.get(i));
        i+=1;
        }
        return isCorrect;
    }

    public void countParentheses(String parentheses){
        //Check if is an openParentheses
        if (parentheses.equals("(")) {
            openparentheses += 1;
        }
        //Check if is an closeParentheses
        if (parentheses.equals(")")) {
            closeparentheses += 1;
        }
    }

    public boolean checkParentheses() {
        boolean okGrammar = true;
        if (openparentheses == closeparentheses) {
            okGrammar = true;
            System.out.println("Parentheses are right");
        } else {
            okGrammar = false;
            System.out.println("Parentheses aren't right");
        }
        return okGrammar;
    }
}
