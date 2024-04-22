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
    private final String ticker;
    private final JsonParser parser = new JsonParser();
    private final HttpClient client = HttpClient.newHttpClient();

    public Divisa(String ticker) {
        this.direccion += ticker.toUpperCase();
        this.ticker = ticker.toUpperCase();
    }

    private  JsonObject  getCoeficienteDeConversion() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        JsonObject mapaDeRespuesta = parser.parse(json).getAsJsonObject();
        return mapaDeRespuesta.get("conversion_rates").getAsJsonObject();
    }

    public double convertirA(double cantidad,Divisa divisa) throws SecurityException,
                            IOException,InterruptedException,IllegalArgumentException,NullPointerException {
        JsonObject coeficienteDeConversion = getCoeficienteDeConversion();
        if (coeficienteDeConversion.get(divisa.toString()) == null) {
            throw new IllegalArgumentException("Ticker no válido");
        }
        return  coeficienteDeConversion.get(divisa.toString()).getAsDouble()*cantidad;
    }

    @Override
    public String toString() { return ticker; }
}
