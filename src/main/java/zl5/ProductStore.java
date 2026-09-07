package zl5;

import java.util.List;

public class ProductStore {
    public static void main(String[] args) {

        List<Product> products = List.of(
                new Electronics("iPhone 16", 3699, "Elektronika", 12),
                new Electronics("Laptop Lenovo", 5699, "Elektronika", 24),
                new FoodProduct("Jabłko", 1.99, "Żywność", "14.09.2026"),
                new FoodProduct("Mleko", 3.99, "Żywność", "10.09.2026"),
                new FoodProduct("Kiełbasa", 7.99, "Żywność", "18.09.2026"));

        for (Product product : products) {
            System.out.println(product.getDescription());
        }
    }
}
