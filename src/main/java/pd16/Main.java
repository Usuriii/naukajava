package pd16;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;

import pd16.gateway.PostsGateway;
import pd16.gateway.TodosGateway;
import pd16.gateway.UserGateway;
import pd16.gateway.WeatherGateway;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();

        UserGateway userGateway = new UserGateway(objectMapper, httpClient);
        TodosGateway todosGateway = new TodosGateway(objectMapper, httpClient);
        PostsGateway postsGateway = new PostsGateway(objectMapper, httpClient);
        WeatherGateway weatherGateway = new WeatherGateway(objectMapper, httpClient);

        var service = UserReportService.initialize(userGateway, todosGateway, postsGateway, weatherGateway);
        UserReport userReport = service.getUserReport(5L);
        System.out.println("User:");
        System.out.println(userReport.getUser());
        System.out.println("Completed todos");
        System.out.println(userReport.getTodos());
        System.out.println("Last three posts");
        System.out.println(userReport.getPosts());
        System.out.println("Weather");
        System.out.println(userReport.getWeather());
    }
}
