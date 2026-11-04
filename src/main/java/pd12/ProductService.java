package pd12;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductService {

    public static List<Map.Entry<String, BigDecimal>>  totalIncome (List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::name,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            BigDecimal reduce = list.stream()
                                    .map(product -> product.price().multiply(BigDecimal.valueOf(product.quantity())))
                                    .findAny()
                                    .orElseThrow();
                            return reduce;
                        })
                ))
                .entrySet()
                .stream().toList();
    }

    public static List<Map.Entry<String, Map<BigDecimal, BigDecimal>>> totalAndAverage (List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            BigDecimal income = list.stream()
                                    .map(product -> product.price().multiply(BigDecimal.valueOf(product.quantity())))
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                            BigDecimal average = list.stream()
                                    .map(Product::price)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                                    .divide(BigDecimal.valueOf(list.size()), 2, RoundingMode.HALF_UP);
                            return Map.of(income, average);
                        })
                ))
                .entrySet().stream()
                .toList();
    }

    public static List<Product> top3 (List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing((Product product) -> product.price().multiply(BigDecimal.valueOf(product.quantity())))
                        .reversed())
                .limit(3)
                .toList();
    }

    public static List<Map.Entry<String, BigDecimal>> incomeByCategory (List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                                    BigDecimal reduce = list.stream()
                                            .map(product -> product.price().multiply(BigDecimal.valueOf(product.quantity())))
                                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                                    return reduce;
                                }
                        )
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .toList();
    }

    public static List<Map.Entry<String, BigDecimal>> averagePriceByCategory (List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            BigDecimal divide = list.stream()
                                    .map(Product::price)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                                    .divide(BigDecimal.valueOf(list.size()), 2, RoundingMode.HALF_UP);
                            return divide;
                        })
                ))
                .entrySet().stream()
                .toList();
    }

    public static void dayWithHighestIncome (List<Product> products) {
        products.stream()
                .max(Comparator.comparing(Product::price))
                .map(Product::date)
                .orElseThrow();
    }
}

