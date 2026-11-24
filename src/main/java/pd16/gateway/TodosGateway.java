package pd16.gateway;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

import lombok.RequiredArgsConstructor;
import pd16.Todos;

@RequiredArgsConstructor
public class TodosGateway {

    private final ObjectMapper objectMapper;
    private final ApiClient apiClient;

    public List<Todos> getTodos(Long userId) throws IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";

        HttpRequest request = apiClient.getRequest(url);
        String body = apiClient.getResponseBody(request);

        List<Todos> deserializedTodos = objectMapper.readValue(body, new TypeReference<>() {
        });

        return deserializedTodos.stream().filter(Todos::isCompleted).toList();
    }
}
