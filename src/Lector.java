import java.io.BufferedReader;
import java.io.FileReader;

public class Lector {
    public String leertxt(String direccion){
        String texto = "";

        try {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfReader = "";

            while ((bfReader = bf.readLine()) != null){
                temp = temp+bfReader+"\n";
            }

            texto = temp;
        } catch (Exception e){
            System.err.println("No se encontró el archivo");
        }
        return texto;
    }
}
