package pd16.gateway;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

import lombok.RequiredArgsConstructor;
import pd16.Posts;

@RequiredArgsConstructor
public class PostsGateway {

    private final ObjectMapper objectMapper;
    private final ApiClient apiClient;

    public List<Posts> getPosts(Long userId) throws IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/users/" + userId + "/posts";

        HttpRequest request = apiClient.getRequest(url);
        String body = apiClient.getResponseBody(request);

        List<Posts> deserializedPosts = objectMapper.readValue(body, new TypeReference<>() {
        });

        return deserializedPosts.stream().skip(deserializedPosts.size() - 3).limit(3).toList();
    }
}
