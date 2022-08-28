public class Consola {
    public static void main(String[] args) {

        Lector reader = new Lector();
        String s1 = reader.leertxt("data\\program.txt");
        System.out.println(s1);

    }
}