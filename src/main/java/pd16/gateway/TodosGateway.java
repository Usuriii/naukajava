package pd16.gateway;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import lombok.RequiredArgsConstructor;
import pd16.Todos;

@RequiredArgsConstructor
public class TodosGateway {

    private final ObjectMapper objectMapper;
    private final HttpClient client;

    public List<Todos> getTodos(Long userId) throws IOException, InterruptedException {
        HttpRequest getTodos = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId + "/todos"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        HttpResponse<String> getTodosResponse = client.send(getTodos, HttpResponse.BodyHandlers.ofString());
        if (getTodosResponse.statusCode() != 200) {
            throw new IOException("User API returned status: " + getTodosResponse.statusCode());
        }
        String todosBody = getTodosResponse.body();

        List<Todos> deserializedTodos = objectMapper.readValue(todosBody, new TypeReference<>() {
        });

        return deserializedTodos.stream().filter(Todos::isCompleted).toList();
    }
}
