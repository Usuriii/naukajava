package pd16.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpRequest;

import lombok.RequiredArgsConstructor;
import pd16.Weather;

@RequiredArgsConstructor
public class WeatherGateway {

    private final ObjectMapper objectMapper;
    private final ApiClient apiClient;

    public Weather getWeather(String latitude, String longitude) throws IOException, InterruptedException {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" +
                latitude +
                "&longitude=" +
                longitude +
                "&current=temperature_2m";

        HttpRequest request = apiClient.getRequest(url);
        String body = apiClient.getResponseBody(request);

        return objectMapper.readValue(body, Weather.class);
    }
}
