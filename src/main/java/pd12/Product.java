package pd12;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Product(LocalDate date, String id, String name, String category, int quantity, BigDecimal price) {
}
