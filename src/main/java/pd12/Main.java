package pd12;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Config config = objectMapper.readValue(Path.of("src/main/java/pd12/config.json").toFile(), Config.class);
        Path inputPath = Path.of(config.inputPath);
        Path outputPath = Path.of(config.outputPath);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(config.dateFormat);
        List<Product> products = loadProducts(inputPath);

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputPath.resolve("report.json").toFile(), products);

        String totalAndAverage = ProductService.totalAndAverage(products).stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));

        Files.writeString(outputPath.resolve("summary.csv"), totalAndAverage, StandardCharsets.UTF_8);
    }

    public static List<Product> loadProducts(Path path) throws IOException {
        try (var lazyReader = Files.lines(path)) {
            return lazyReader
                    .skip(1)
                    .map(c -> c.split(",", -1))
                    .map(array -> new Product(
                            LocalDate.parse(array[0]),
                            array[1],
                            array[2],
                            array[3],
                            Integer.parseInt(array[4]),
                            new BigDecimal(array[5])))
                    .toList();
        }
    }
}
