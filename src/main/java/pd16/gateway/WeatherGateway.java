package pd16.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import lombok.RequiredArgsConstructor;
import pd16.Weather;

@RequiredArgsConstructor
public class WeatherGateway {

    private final ObjectMapper objectMapper;
    private final HttpClient client;

    public Weather getWeather(String latitude, String longitude) throws IOException, InterruptedException {
        HttpRequest getWeather = HttpRequest.newBuilder()
                .uri(
                        URI.create(
                                "https://api.open-meteo.com/v1/forecast?latitude=" +
                                        latitude +
                                        "&longitude=" +
                                        longitude +
                                        "&current=temperature_2m"
                        )
                )
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        HttpResponse<String> getWeatherResponse = client.send(getWeather, HttpResponse.BodyHandlers.ofString());
        if (getWeatherResponse.statusCode() != 200) {
            throw new IOException("User API returned status: " + getWeatherResponse.statusCode());
        }
        String weatherBody = getWeatherResponse.body();

        return objectMapper.readValue(weatherBody, Weather.class);
    }
}
