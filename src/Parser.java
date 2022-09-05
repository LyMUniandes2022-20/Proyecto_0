import java.util.ArrayList;

public class Parser {
    /*
    reservedWords : are used how reserved words in our language, is an ArrayList cuz using "PROC" the user can add new reserved words.
    */
    private Lexer lexer;
    private ArrayList<String> variablesCreated = new ArrayList<>();
    private ArrayList<String> instructionsList = new ArrayList<>();
    private ArrayList<String> conditionalsList = new ArrayList<>();
    private ArrayList<String> conditionsList = new ArrayList<>();

    public Parser(){
        lexer = new Lexer();
        setReservedWords();
        setInstructionsList();
        setConditionsList();
        setConditionalList();
    }

    public void setConditionsList(){
        conditionsList.add("isfacing");
        conditionsList.add("isValid");
        conditionsList.add("canWalk");
        conditionsList.add("not");
    }

    public ArrayList<String> getConditionsList(){
        return conditionsList;
    }

    public void setConditionalList(){
        conditionalsList.add("if");
        conditionalsList.add("else");
        conditionalsList.add("while");
        conditionalsList.add("do");
        conditionalsList.add("od");
        conditionalsList.add("fi");
        conditionalsList.add("repeatTimes");
        conditionalsList.add("per");
    }

    public ArrayList<String> getConditionalList(){
        return conditionalsList;
    }

    public void setInstructionsList(){
        instructionsList.add("walk");
        instructionsList.add("jump");
        instructionsList.add("jumpTo");
        instructionsList.add("veer");
        instructionsList.add("look");
        instructionsList.add("drop");
        instructionsList.add("grab");
        instructionsList.add("get");
        instructionsList.add("free");
        instructionsList.add("pop");
    }

    public ArrayList<String> getInstructionsList(){
        return instructionsList;
    }
    public ArrayList<String> getVariablesC(){
        return variablesCreated;
    }

    private ArrayList<String> reservedWords = new ArrayList<String>();
    public void setReservedWords() {
        reservedWords.add("var");
        reservedWords.add("PROC"); //Not yet
        reservedWords.add("CORP");
        reservedWords.add("PROG");
        reservedWords.add("GORP");
        //commandsWords
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
        //ConditionalWords
        reservedWords.add("if");
        reservedWords.add("else");
        reservedWords.add("while");
        reservedWords.add("do");
        reservedWords.add("od");
        reservedWords.add("fi");
        reservedWords.add("repeatTimes");
        reservedWords.add("per");
        //ConditionWords
        reservedWords.add("isfacing");
        reservedWords.add("isValid");
        reservedWords.add("canWalk");
        reservedWords.add("not");
        //Orientation
        reservedWords.add("north");
        reservedWords.add("south");
        reservedWords.add("east");
        reservedWords.add("west");
        //Direction
        reservedWords.add("right");
        reservedWords.add("left");
        reservedWords.add("front");
        reservedWords.add("back");
        reservedWords.add("around");
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

	public boolean verifier(ArrayList<String> tokens) {
        //This function will return true or false. 
		//True if the grammar its ok and viceversa
        boolean okProgram = true;
        boolean okTokens = true;
        boolean okParentheses = true;
        boolean okBlockProc = true;
        boolean okConditionals = true;
        String text = "";

        //add the variables to the language
        if(tokens.get(0).equals("var")){
            boolean checkVar =  checkVarLine (tokens);
            return checkVar;

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
            okBlockProc = checkBlockProc(tokens);
        }
        //To check that the structure of conditionals are ok

        //Iteration of the array to verify if all tokens are in the lenguage
        boolean addingToConditional = false;
        ArrayList<String> tokensConditional = new ArrayList<>();
		for (String token : tokens) {
            if(reservedWordsVerifier(token)){
                text += token+" ";
            }
            if (reservedWordsVerifier(token) == false){
                okTokens = false;
                System.out.println("Failed token: "+token);
            }

            //with these 3 "if conditions" we will add all the tokens that belong to a procedure
            if (token.equals("if") || token.equals("while")) {
                addingToConditional = true;
            }
            if (addingToConditional) {
                tokensConditional.add(token);
            }
            if (token.equals("fi") || token.equals("od")) {
                addingToConditional = false;
            }
        }
        okConditionals = checkConditionals(tokensConditional);
        okParentheses = checkParentheses();
        System.out.println(text);
        
        return okProgram;
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
        //Comprove if the parentheses are opening and closing correctly
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

    //This function will aprove if a procedure starts with PROC and ends with CORP
    public boolean checkBlockProc(ArrayList<String> blockOfTokens){
        boolean okBlockProc = false;
        if (blockOfTokens.get(0).equals("PROC") && blockOfTokens.get(blockOfTokens.size()-1).equals("CORP")) {
            okBlockProc = true;
        }
        return okBlockProc;
    }

    public boolean checkConditionals(ArrayList<String> tokenConditionals){
        boolean okConditionals = false;
        boolean addingConditionStructure = false;
        boolean toIsValid = false;
        ArrayList<String> checkingConditionList = new ArrayList<>();
        for (String token : tokenConditionals) {
            countParentheses(token);
            countBrackets(token);
            if (conditionsList.contains(token) || instructionsList.contains(token)) {
                addingConditionStructure = true;
                if (token.equals("isValid")) {
                    toIsValid = true;
                }
            }
            if (addingConditionStructure) {
                checkingConditionList.add(token);
                if (token.equals(")")) {
                    addingConditionStructure = false;
                    if (toIsValid) {
                        addingConditionStructure = true;
                        toIsValid = false;
                    }
                    if (addingConditionStructure == false) {
                        verifierOfConditionsStructure(checkingConditionList);
                    }
                }
            }
        }
        return okConditionals;
    }
        //Conditions of isValid(ins, n)
        //Example: isValid(jump(5), 7) = [isValid, (, jump, (, 5, ), ",", 7, )]


    private void verifierOfConditionsStructure(ArrayList<String> tokensList) {
        //example: Walk(1) = [walk, (, 1, )]
        boolean okStructure = false;
        String[] numListParameter = {"walk", "jump", "drop", "grab", "get", "free", "pop"};
        String[] directions = {"front", "right", "left", "back"};
        String[] orientations = {"north", "south", "west", "east"};
        ArrayList<String> dirParameter = new ArrayList<>();
        ArrayList<String> numParameter = new ArrayList<>();
        ArrayList<String> orientParameter = new ArrayList<>();

        for (String orientation : orientParameter) {
            orientParameter.add(orientation);
        }
        for (String direction: dirParameter) {
            dirParameter.add(direction);
        }

        for (String inst : numListParameter) {
            numParameter.add(inst);
        }
        //conditions with one parameter: walk, jump, veer, look, drop, grab, get, free, isfacing
        if (tokensList.size() == 4) {
            if (numParameter.contains(tokensList.get(0))) {
                boolean isNumeric = isNumeric(tokensList.get(2));
                if (isNumeric) {
                    okStructure = true;
                }
            }
            if (tokensList.get(0).equals("veer")) {
                if (tokensList.get(2).equals("left") || tokensList.get(2).equals("right") || tokensList.get(2).equals("around")) {
                    okStructure = true;
                }
            }
            if (tokensList.get(0).equals("look")) {
                if (orientParameter.contains(tokensList.get(2))) {
                    okStructure = true;
                }
            }
            if (tokensList.get(0).equals("isfacing")) {
                if (orientParameter.contains(tokensList.get(2))){
                    okStructure = true;
                }
            }
        }
        //example: canWalk(north,1) = [canWalk, (, north, ",", 1, )]
        //conditions with two parameters: jumpTo, walk, isValid, canWalk
        if (tokensList.size() == 6) {
            //Conditions of jumpTo(n,n)
            if (tokensList.get(0).equals("jumpTo")) {
                boolean isNumeric1 = isNumeric(tokensList.get(2));
                boolean isNumeric2 = isNumeric(tokensList.get(4));
                if (isNumeric1 == isNumeric2) {
                    okStructure = true;
                }
            }
            //Conditions of walk(d, n)
            if (tokensList.get(0).equals("walk")) {
                if (dirParameter.contains(tokensList.get(2))) {
                    boolean isNumeric = isNumeric(tokensList.get(4));
                    if (isNumeric) {
                        okStructure = true;
                    }
                }
            }
            //Conditions of walk(o,n)
            if (tokensList.get(0).equals("walk")) {
                if (orientParameter.contains(tokensList.get(2))) {
                    boolean isNumeric = isNumeric(tokensList.get(4));
                    if (isNumeric) {
                        okStructure = true;
                    }
                }
            }
            //Conditions of isValid(ins, n)
        //Example: isValid(jump(5), 7) = [isValid, (, jump, (, 5, ), ",", 7, )]
        if (tokensList.size() > 6) {
            
        }
        }
    }

    //This functions will be use to check if commands are correctly written
    public boolean checkCommands(ArrayList<String> commandsList){
        boolean okCommand = false;

        return okCommand;
    }

    public void countBrackets(String token){
        if (token.equals("{")) {
            openbrackets += 1;
        }
        if (token.equals("}")) {
            closebrackets += 1;
        }
    }

    public boolean checkBrackets(){
        boolean okGrammar = true;
        if (openbrackets == closebrackets) {
            okGrammar = true;
            System.out.println("Brackets are right");
        } else {
            okGrammar = false;
            System.out.println("Brackets aren't right");
        }
        return okGrammar;
    }

    public static boolean isNumeric(String cadena) {
        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
}
