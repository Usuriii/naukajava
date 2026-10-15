package pd8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class Product implements Comparable<Product> {
    private final Long id;
    private final String name;
    private final String category;
    private final BigDecimal price;
    @Setter
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
