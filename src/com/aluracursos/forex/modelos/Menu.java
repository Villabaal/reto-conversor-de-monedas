package com.aluracursos.forex.modelos;

import com.aluracursos.forex.Excepciones.Salir;

import javax.naming.directory.InvalidAttributeValueException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Menu {
    private final Divisa[] divisaBase;
    private final Scanner lectura = new Scanner(System.in);
    private final Logger archivador = new Logger("historial");

    public Menu(String... tickers) {
        divisaBase = new Divisa[tickers.length];
        for (int i = 0; i < tickers.length; i++) {
            divisaBase[i] = new Divisa(tickers[i]);
        }
    }
    private void imprimirMenu() {
        System.out.println("******************************************************************************************");
        System.out.println("Selecciona el número para elegir la divisa base desde la cual convertir:");
        for (int i = 0; i < divisaBase.length; i++) {
            var cadena = (i + 1) + ".- " + divisaBase[i];
            System.out.println(cadena);
        }
        System.out.println((divisaBase.length + 1) + ".- salir");
        System.out.println("******************************************************************************************");
    }

    private String leerDelTeclado() throws Salir, InvalidAttributeValueException, IOException, InterruptedException {
        System.out.print("Introduce el número: ");
        int indice = Integer.parseInt( lectura.nextLine() );
        if ( divisaBase.length+1 == indice  )
            throw new Salir();
        if ( 1 > indice )
            throw new InvalidAttributeValueException("menor a uno?");
        if ( divisaBase.length+1 < indice)
            throw new ArrayIndexOutOfBoundsException("número menor porfavor");
        Divisa divisa = divisaBase[indice-1];
        System.out.print("\nIntroduce el ticker de la divisa a la cual quieres convertir: ");
        String ticker = lectura.nextLine().toUpperCase();
        Divisa divisaDestino = new Divisa(ticker);
        System.out.print("\nCantidad de la divisa base que se quiere convertir: ");
        double cantidad = Double.parseDouble( lectura.nextLine() );
        double cantidadConvertida = divisa.convertirA(cantidad,divisaDestino);
        return  "%.2f %s = %.2f %s"
                .formatted(cantidad,divisa.toString(),cantidadConvertida,ticker);
    }

    public void menu() throws InvalidAttributeValueException,
            NumberFormatException, IOException, InterruptedException, Salir {
        imprimirMenu();
        String cadenaDeSalida;cadenaDeSalida = leerDelTeclado();
        archivador.save(cadenaDeSalida);
        System.out.println("\n"+cadenaDeSalida);
        System.out.println("Presiona Enter para seguir");
        lectura.nextLine();
    }
}
