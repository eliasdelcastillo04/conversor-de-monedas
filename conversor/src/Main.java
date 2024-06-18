import com.clases.Moneda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalTime;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        Gson gson = new Gson();
        ArrayList<String> historial = new ArrayList<String>();


        do {
            System.out.println("************************************************************************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda de DC-CS");
            System.out.println("\n1) Dólar ==> Peso Argentino");
            System.out.println("2) Peso Argentino ==> Dólar");
            System.out.println("3) Dólar ==> Real Brasileño");
            System.out.println("4) Real Brasileño ==> Dólar");
            System.out.println("5) Dólar ==> Peso Colombiano");
            System.out.println("6) Peso Colombiano ==> Dólar");
            System.out.println("7) Salir");
            System.out.println("\nElija una opción válida:");
            System.out.println("************************************************************************************************");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    convertirMoneda("USD", "ARS", scanner,historial);
                    break;
                case 2:
                    convertirMoneda("ARS", "USD", scanner,historial);
                    break;
                case 3:
                    convertirMoneda("USD", "BRL", scanner,historial);
                    break;
                case 4:
                    convertirMoneda("BRL", "USD", scanner,historial);
                    break;
                case 5:
                    convertirMoneda("USD", "COP", scanner,historial);
                    break;
                case 6:
                    convertirMoneda("COP", "USD", scanner,historial);
                    break;
                case 7:
                    System.out.println("************************************************************************************************");
                    System.out.println("Gracias por usar el conversor de monedas de DC-CS");
                    System.out.println("************************************************************************************************");
                    break;
                default:
                    System.out.println("********************************");
                    System.out.println("Ingrese una opción válida");
                    System.out.println("********************************");
                    break;
            }

            FileWriter escritura = new FileWriter("conversiones.json");
            escritura.write(gson.toJson(historial));
            escritura.close();
            System.out.println("Finalizó la ejecución del programa!");
        } while (opcion != 7);


    }

    public static void convertirMoneda(String fromCurrency, String toCurrency, Scanner scanner ,ArrayList list) {
        String url = "https://v6.exchangerate-api.com/v6/39c79f05baa526e623ec6bc1/latest/" + fromCurrency;
        LocalTime horaActual = LocalTime.now();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                Moneda moneda = gson.fromJson(response.body(), Moneda.class);

                Map<String, Double> conversionRates = moneda.getConversion_rates();
                Double conversionRate = conversionRates.get(toCurrency);

                if (conversionRate != null) {
                    System.out.printf("Ingrese la cantidad de %s que desea convertir a %s:%n", fromCurrency, toCurrency);
                    double cantidad = scanner.nextDouble();

                    double resultado = cantidad * conversionRate;
                    System.out.printf("%.10f %s equivale a %.10f %s%n", cantidad, fromCurrency, resultado, toCurrency);
                    list.add(String.format("(%.10f %s equivale a %.10f %s , La hora de la converción:%s",cantidad, fromCurrency, resultado, toCurrency,horaActual));
                } else {
                    System.out.printf("No se encontró la tasa de conversión de %s a %s%n", fromCurrency, toCurrency);
                }
            } else {
                System.out.println("Error al obtener los datos del servidor");
            }
        } catch (Exception e) {
            System.out.println("Error en la solicitud HTTP: " + e.getMessage());
        }
    }
}
