package pd16;

import io.vavr.control.Try;

import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import pd16.gateway.PostsGateway;
import pd16.gateway.TodosGateway;
import pd16.gateway.UserGateway;
import pd16.gateway.WeatherGateway;
import pd16.user.User;

@RequiredArgsConstructor(staticName = "initialize")
public class UserReportService {

    private final UserGateway userGateway;
    private final TodosGateway todosGateway;
    private final PostsGateway postsGateway;
    private final WeatherGateway weatherGateway;

    public UserReport getUserReport(Long userId) throws IOException {
        User user = Try.of(() -> userGateway.getUser(userId)).getOrElseThrow(throwable ->
                new IOException("User API failed" + throwable)
        );

        List<Todos> todos = Try.of(() -> todosGateway.getTodos(userId))
                .onFailure(throwable -> System.err.println("Todos API failed: " + throwable.getMessage()))
                .getOrElse(List.of());

        List<Posts> posts = Try.of(() -> postsGateway.getPosts(userId))
                .onFailure(throwable -> System.err.println("Posts API failed: " + throwable.getMessage()))
                .getOrElse(List.of());

        String latitude = user.getAddress().getGeo().getLat();
        String longitude = user.getAddress().getGeo().getLng();

        Weather weather = Try.of(() -> weatherGateway.getWeather(latitude, longitude))
                .onFailure(throwable -> System.err.println("Weather API failed: " + throwable.getMessage()))
                .getOrElse(() -> null);

        return new UserReport(user, todos, posts, weather);
    }
}
