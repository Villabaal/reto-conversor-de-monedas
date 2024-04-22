package com.aluracursos.forex.modelos;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private final String archivo;

    public Logger(String nombreDeArchivo) {
        this.archivo = nombreDeArchivo+".txt";
    }

    public void save(String mensaje) throws IOException {
        LocalDateTime locaDate = LocalDateTime.now();
        FileWriter escritura = new FileWriter(archivo,true);
        escritura.write( locaDate+": "+mensaje+"\n" );
        escritura.close();
    }
}
