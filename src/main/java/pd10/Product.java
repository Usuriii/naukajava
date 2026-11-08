package pd10;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Product {
    private final Long id;
    private final String name;
    private final String category;
    private BigDecimal price;
    private int stock;
    private int sold;
    private double rating;

    public Product(Long id, String name, String category, BigDecimal price, int stock, int sold, double rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.sold = sold;
        this.rating = rating;

        productValidator(name, category, price, stock, sold, rating);
    }

    private static void productValidator(String name, String category, BigDecimal price, int stock, int sold, double rating) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is empty or null");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category is empty or null");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be negative or zero");
        }
        if (sold < 0) {
            throw new IllegalArgumentException("Sold cannot be negative");
        }
        if (rating < 1.00 || rating > 5.00) {
            throw new IllegalArgumentException("Rating range is 1.0 - 5.0");
        }
    }
}
