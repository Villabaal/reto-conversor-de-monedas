

import com.aluracursos.forex.modelos.Menu;

import javax.naming.directory.InvalidAttributeValueException;
import java.io.IOException;


public class Main {
    private static final Menu menu = new Menu("usd","mxn","eur","cny","jpy");

    public static void main(String[] args) {
        try {
            menu.loop();
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

}