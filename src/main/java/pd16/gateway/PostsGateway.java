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
import pd16.Posts;

@RequiredArgsConstructor
public class PostsGateway {

    private final ObjectMapper objectMapper;
    private final HttpClient client;

    public List<Posts> getPosts(Long userId) throws IOException, InterruptedException {
        HttpRequest getPosts = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId + "/posts"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        HttpResponse<String> getPostsResponse = client.send(getPosts, HttpResponse.BodyHandlers.ofString());
        if (getPostsResponse.statusCode() != 200) {
            throw new IOException("User API returned status: " + getPostsResponse.statusCode());
        }
        String postsBody = getPostsResponse.body();

        List<Posts> deserializedPosts = objectMapper.readValue(postsBody, new TypeReference<>() {
        });

        return deserializedPosts.stream().skip(deserializedPosts.size() - 3).limit(3).toList();
    }
}
