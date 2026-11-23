package pd16.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import lombok.RequiredArgsConstructor;
import pd16.user.User;

@RequiredArgsConstructor
public class UserGateway {

    private final ObjectMapper objectMapper;
    private final HttpClient client;

    public User getUser(Long userId) throws IOException, InterruptedException {
        HttpRequest getUser = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        HttpResponse<String> getUserResponse = client.send(getUser, HttpResponse.BodyHandlers.ofString());
        if (getUserResponse.statusCode() != 200) {
            throw new IOException("User API returned status: " + getUserResponse.statusCode());
        }
        String userBody = getUserResponse.body();

        return objectMapper.readValue(userBody, User.class);
    }
}
