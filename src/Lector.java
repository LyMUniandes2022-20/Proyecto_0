import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Lector {
    public ArrayList<String> leertxt(String direccion){
        String texto = "";
        ArrayList<String> txtLines = new ArrayList<>();

        try {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfReader = "";

            while ((bfReader = bf.readLine()) != null){
                temp = temp+bfReader+"\n";
                txtLines.add(bfReader);
            }
            texto = temp;
        } catch (Exception e){
            System.err.println("No se encontró el archivo");
        }
        return txtLines;
    }

    /**
     * @param route route of .txt which has a program
     * @return queue that contains all of the lines of the program
     * Than method will be used if prog/gorp is always used
     */
    public Queue<String> readprog(String route){

        Queue<String> strings = new LinkedList<String>();
        /* QUEUE: 
        To add .add
        To get .poll
        To clean .clear
        To get without delete head of queue .peek
        */

        try {
            BufferedReader bf = new BufferedReader(new FileReader(route));
            String temp = "";
            while((temp = bf.readLine()) != "GORP"){
                if (temp != "PROG"){
                    strings.add(temp);
                }   
            }

        }catch (Exception e) {
            System.err.println("The file can't be found");
        }
        return strings;

    }
    
}
