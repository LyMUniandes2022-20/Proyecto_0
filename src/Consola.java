import java.util.ArrayList;

public class Consola {
    public static void main(String[] args) {

        Lector reader = new Lector();
        Parser parser = new Parser();
        ArrayList<String> s1 = reader.leertxt("data\\program.txt");
        System.out.println(s1);
        System.out.println("\n");
        String tokens[] = {"PROC", "goNorth", "(", ")"};
        parser.verifier(s1, tokens);
    }
}