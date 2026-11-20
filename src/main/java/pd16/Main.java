package pd16;

import java.io.IOException;
//        PRACA DOMOWA PD-16 — API Gateway do zewnętrznych serwisów
//
//        Zbuduj "agregator danych" który pobiera informacje z 3 publicznych API (bez klucza):
//
//        JSONPlaceholder (https://jsonplaceholder.typicode.com/):
//        GET /users/{id}  — dane użytkownika
//        GET /users/{id}/todos — lista zadań
//        GET /users/{id}/posts — lista postów
//
//        Open-Meteo (https://api.open-meteo.com/) — pogoda dla lokalizacji użytkownika
//
//        REST Countries (https://restcountries.com/v3.1/name/%7Bname%7D) — dane o kraju
//
//        Wymagania:
//        a) Klasa ApiGateway z metodą getUserReport(Long userId) → UserReport ////////////////////////
//        b) UserReport = user + todos (tylko incomplete) + posts (ostatnie 3) + pogoda /////////////////////
//        c) Pobierz todos + posts + pogodę RÓWNOLEGLE przez sendAsync + CompletableFuture.allOf ////////////////
//        d) Obsłuż błędy każdego API niezależnie — jeden failed API nie psuje całości ////////////
//        e) Zmierz czas: pobieranie sekwencyjne vs równoległe (Instant.now())

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ApiGateway apiGateway = ApiGateway.initialize();
        long start = System.nanoTime();
        UserReport userReport = apiGateway.getUserReport(5L);
        System.out.println(System.nanoTime() - start);
        System.out.println("User:");
        System.out.println(userReport.getUser());
        System.out.println("Completed todos");
        System.out.println(userReport.getTodos());
        System.out.println("Last three posts");
        System.out.println(userReport.getPosts());
        System.out.println("Weather");
        System.out.println(userReport.getWeather());
    }
}
