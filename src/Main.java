

import com.aluracursos.forex.Excepciones.Salir;
import com.aluracursos.forex.modelos.Menu;

import javax.naming.directory.InvalidAttributeValueException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Main {
    private static final Menu menu = new Menu("usd","mxn","eur","cny","jpy");

    private static void esperarParaReiniciar() throws RuntimeException, InterruptedException {
        TimeUnit.SECONDS.sleep(3);
    }

    public static void main(String[] args) throws IOException , InterruptedException {
        while (true) {
            try {
                menu.menu();
            } catch (Salir e){
                System.out.println(e.getMessage());
                break;
            } catch (InvalidAttributeValueException |
                     IllegalArgumentException |
                     ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                esperarParaReiniciar();
            }
        }
    }

}