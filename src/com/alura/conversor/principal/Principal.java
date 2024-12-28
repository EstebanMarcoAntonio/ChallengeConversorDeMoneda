package com.alura.conversor.principal;

import com.alura.conversor.modelos.MonedasApis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        Gson gson = new GsonBuilder().create();

        while (true) {
            System.out.println("*****************************************");
            System.out.println(" ");
            System.out.println("Sea bienvenido/a al Conversor de Moneda:");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida:");
            System.out.println("*****************************************");

            var opcion = lectura.nextLine();
            String tipoMoneda = "";
            String conversionMoneda = "";

            if (opcion.equalsIgnoreCase("1")) {
                tipoMoneda = "USD";
                conversionMoneda = "ARS";
            } else if (opcion.equalsIgnoreCase("2")) {
                tipoMoneda = "ARS";
                conversionMoneda = "USD";
            } else if (opcion.equalsIgnoreCase("3")) {
                tipoMoneda = "USD";
                conversionMoneda = "BRL";
            } else if (opcion.equalsIgnoreCase("4")) {
                tipoMoneda = "BRL";
                conversionMoneda = "USD";
            } else if (opcion.equalsIgnoreCase("5")) {
                tipoMoneda = "USD";
                conversionMoneda = "COP";
            } else if (opcion.equalsIgnoreCase("6")) {
                tipoMoneda = "COP";
                conversionMoneda = "USD";
            } else if (opcion.equalsIgnoreCase("7")) {
                System.out.println("Vuelva pronto");
                break;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
                continue;
            }

            String direccionUrl = "https://v6.exchangerate-api.com/v6/00890faf973cde2c0810597e/latest/" + tipoMoneda;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccionUrl))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                MonedasApis miMoneda = gson.fromJson(json, MonedasApis.class);

                if ("success".equalsIgnoreCase(miMoneda.getResult())) {
                    System.out.println("Ingrese el monto a convertir:");
                    double monto = lectura.nextDouble();
                    lectura.nextLine();

                    double tasaConversion = 0;
                    switch (conversionMoneda) {
                        case "ARS":
                            tasaConversion = miMoneda.getConversion_rates().getARS();
                            break;
                        case "USD":
                            tasaConversion = miMoneda.getConversion_rates().getUSD();
                            break;
                        case "BRL":
                            tasaConversion = miMoneda.getConversion_rates().getBRL();
                            break;
                        case "COP":
                            tasaConversion = miMoneda.getConversion_rates().getCOP();
                            break;
                        default:
                            System.out.println("No se encontró la tasa de conversión para " + conversionMoneda);
                            continue;
                    }

                    double resultado = monto * tasaConversion;
                    System.out.printf("El resultado de la conversión es: %.10f %s\n", resultado, conversionMoneda);
                } else {
                    System.out.println("Error al obtener los datos: " + miMoneda.getResult());
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
