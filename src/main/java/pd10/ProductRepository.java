package pd10;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(staticName = "initialize")
public class ProductRepository {
    private final List<Product> products = List.of(
            new Product(5L, "iPhone 16 Pro", "Electronic", new BigDecimal("3699.99"), 22, 1, 4.0),
            new Product(32L, "Hulajnoga JEEP", "Electronic", new BigDecimal("1279.54"), 7, 3, 2.0),
            new Product(2L, "Laptop lenovo", "Electronic", new BigDecimal("4799.44"), 14, 2, 5.0),
            new Product(12L, "PS5 Slim", "Electronic", new BigDecimal("3598.64"), 3, 0, 3.0),
            new Product(6L, "Rower stacjonarny", "Sport", new BigDecimal("1298.98"), 11, 1, 2.0),
            new Product(66L, "Atlas HMS", "Sport", new BigDecimal("1849.99"), 1, 0, 2.0),
            new Product(43L, "Ławka do ćwiczeń", "Sport", new BigDecimal("599.99"), 3, 1, 1.0),
            new Product(45L, "Wioślarz", "Sport", new BigDecimal("1099.00"), 2, 1, 2.0),
            new Product(53L, "Lego Football", "Zabawki", new BigDecimal("550.51"), 6, 2, 1.0),
            new Product(3L, "Lego Kwiaty", "Zabawki", new BigDecimal("330.54"), 12, 4, 1.0),
            new Product(22L, "Piła łańcuchowa", "Warsztat i ogród", new BigDecimal("319.94"), 14, 3, 1.0),
            new Product(26L, "Kosiarka spalinowa", "Warsztat i ogród", new BigDecimal("1319.99"), 7, 3, 2.0),
            new Product(35L, "Glebogryzarka spalinowa", "Warsztat i ogród", new BigDecimal("949.54"), 15, 1, 1.0),
            new Product(55L, "Myjka ciśnieniowa", "Warsztat i ogród", new BigDecimal("1999.99"), 3, 2, 2.0),
            new Product(65L, "Lodówka SAMSUNG", "AGD", new BigDecimal("3499.99"), 6, 1, 3.0),
            new Product(76L, "Pralka ELECTROLUX", "AGD", new BigDecimal("2699.99"), 4, 1, 3.0),
            new Product(4L, "Zmywarka BOSH", "AGD", new BigDecimal("1799.99"), 3, 2, 2.0)
    );

    public List<Product> getProducts() {
        return List.copyOf(products);
    }
}
