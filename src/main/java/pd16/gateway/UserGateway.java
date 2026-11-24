package pd16.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpRequest;

import lombok.RequiredArgsConstructor;
import pd16.user.User;

@RequiredArgsConstructor
public class UserGateway {

    private final ObjectMapper objectMapper;
    private final ApiClient apiClient;

    public User getUser(Long userId) throws IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/users/" + userId;

        HttpRequest request = apiClient.getRequest(url);
        String body = apiClient.getResponseBody(request);

        return objectMapper.readValue(body, User.class);
    }
}
