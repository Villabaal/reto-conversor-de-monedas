package com.aluracursos.forex.modelos;

import javax.naming.directory.InvalidAttributeValueException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private Divisa[] divisaBase;
    private final Scanner lectura = new Scanner(System.in);

    public Menu(String... tickers) {
        divisaBase = new Divisa[tickers.length];
        for (int i = 0; i < tickers.length; i++) {
            divisaBase[i] = new Divisa(tickers[i]);
        }
    }
    public void imprimirMenu(){
        System.out.println("******************************************************************************************");
        System.out.println("Selecciona el número para elegir la divisa base desde la cual convertir:");
        for (int i = 0; i < divisaBase.length; i++) {
            var cadena = (i+1)+".- "+divisaBase[i];
            System.out.println(cadena);
        }
        System.out.println("******************************************************************************************");
    }
    public void loop() throws InvalidAttributeValueException,
            NumberFormatException, IOException, InterruptedException {
        imprimirMenu();
        System.out.print("Introduce el número: ");
        while (true) {
            int indice = Integer.parseInt( lectura.nextLine() );
            if ( divisaBase.length+1 == indice  ) { break; }
            if ( 1 > indice )
                throw new InvalidAttributeValueException("menor a uno?");
            Divisa divisa = divisaBase[indice-1];
            System.out.print("\nIntroduce el ticker de la divisa a la cual quieres convertir: ");
            String ticker = lectura.nextLine().toUpperCase();
            System.out.print("\nCantidad de la divisa base que se quiere convertir: ");
            double cantidad = Double.parseDouble( lectura.nextLine() );
            double cantidadConvertida = divisa.convertirA(cantidad,ticker);
            String cadenaDeSalida = "%.2f %s = %.2f %s"
                    .formatted(cantidad,divisa.toString(),cantidadConvertida,ticker);
            System.out.println("\n"+cadenaDeSalida);
            System.out.println("Presiona Enter para seguir");
            lectura.nextLine();
            imprimirMenu();
        }
    }
}
