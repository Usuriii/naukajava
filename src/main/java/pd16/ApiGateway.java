package pd16;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import pd16.user.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor(staticName = "initialize")
public class ApiGateway {
    ObjectMapper objectMapper = new ObjectMapper();


    public UserReport getUserReport(Long userId) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest getUser = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        HttpResponse<String> getResponse = client.send(getUser, HttpResponse.BodyHandlers.ofString());
        String userBody = getResponse.body();

        User user = objectMapper.readValue(userBody, User.class);

        HttpRequest getTodos = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId + "/todos"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> askForTodos = client.sendAsync(getTodos, HttpResponse.BodyHandlers.ofString());
        String todosBody = askForTodos.join().body();

        List<Todos> deserializedTodos = objectMapper.readValue(todosBody, new TypeReference<>() {
        });

        List<Todos> completedTodos = deserializedTodos.stream()
                .filter(Todos::isCompleted)
                .toList();

        HttpRequest getPosts = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId + "/posts"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> askForPosts = client.sendAsync(getPosts, HttpResponse.BodyHandlers.ofString());
        String postsBody = askForPosts.join().body();

        List<Posts> deserializedPosts = objectMapper.readValue(postsBody, new TypeReference<>() {
        });

        List<Posts> lastThreePosts = deserializedPosts.stream()
                .skip(deserializedPosts.size() - 3)
                .limit(3)
                .toList();


        String latitude = user.getAddress().getGeo().getLat();
        String longitude = user.getAddress().getGeo().getLng();
        HttpRequest getWeather = HttpRequest.newBuilder()
                .uri(URI.create("https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&current=temperature_2m"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> askForWeather = client.sendAsync(getWeather, HttpResponse.BodyHandlers.ofString());
        String weatherBody = askForWeather.join().body();

        Weather weather = objectMapper.readValue(weatherBody, Weather.class);

        CompletableFuture.allOf(askForTodos.exceptionally(ex -> {
                    System.err.println("Error during TODOS request" + ex.getMessage());
                    return null;
                }),
                askForPosts.exceptionally(ex -> {
                    System.err.println("Error during POSTS request" + ex.getMessage());
                    return null;
                }),
                askForWeather.exceptionally(ex -> {
                    System.err.println("Error during WEATHER request" + ex.getMessage());
                    return null;
                })).join();

        return new UserReport(user, completedTodos, lastThreePosts, weather);
    }

}
