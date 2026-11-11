package pd12;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {

    public static Map<String, BigDecimal> totalIncome(List<Product> products) {
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
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    public static Map<String, Map<BigDecimal, BigDecimal>> totalAndAverage(List<Product> products) {
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
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    public static List<Product> top3(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing((Product product) -> product.price().multiply(BigDecimal.valueOf(product.quantity())))
                        .reversed())
                .limit(3)
                .toList();
    }

    public static Map<String, BigDecimal> incomeByCategory(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                product -> product.price().multiply(BigDecimal.valueOf(product.quantity())),
                                BigDecimal::add
                        ))
                )
                .entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public static Map<String, BigDecimal> averagePriceByCategory(List<Product> products) {
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
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    public static List<LocalDate> dayWithHighestIncome(List<Product> products) {
        Map<LocalDate, BigDecimal> collect = products.stream()
                .collect(Collectors.groupingBy(
                        Product::date,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                product -> product.price().multiply(BigDecimal.valueOf(product.quantity())),
                                BigDecimal::add
                        ))
                )
                .entrySet()
                .stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        BigDecimal maxValue = collect.values().stream().max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);

        return collect.entrySet().stream()
                .filter(p -> p.getValue().compareTo(maxValue) == 0)
                .map(Map.Entry::getKey)
                .toList();
    }
}

