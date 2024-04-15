package com.aluracursos.forex.modelos;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Divisa {
    private String direccion = "https://v6.exchangerate-api.com/v6/255a07d9d43c48593fd34846/latest/";
    private String ticker;
    private final JsonParser parser = new JsonParser();
    private final HttpClient client = HttpClient.newHttpClient();

    public Divisa(String ticker) {
        this.direccion += ticker.toUpperCase();
        this.ticker = ticker.toUpperCase();
    }

    public double convertirA(double cantidad,String ticker) throws SecurityException,
                            IOException,InterruptedException,IllegalArgumentException,NullPointerException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        JsonObject mapaDeRespuesta = parser.parse(json).getAsJsonObject();
        JsonObject coeficienteDeConversion = mapaDeRespuesta.get("conversion_rates").getAsJsonObject();
        if (coeficienteDeConversion.get(ticker) == null) {
            throw new IllegalArgumentException("Ticker no válido");
        }
        return  coeficienteDeConversion.get(ticker).getAsDouble()*cantidad;
    }

    @Override
    public String toString() { return ticker; }
}
