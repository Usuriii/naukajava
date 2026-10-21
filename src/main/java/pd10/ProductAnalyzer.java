package pd10;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductAnalyzer {
    public static void reportWithTopProductpPerCategory(List<Product> products) {
        products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            Optional<Product> productListByRating = list.stream()
                                    .max(Comparator.comparing(Product::getRating));
                            return productListByRating.orElseThrow();
                        })))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void categorizationProductsIntoPremiumAndStandard(List<Product> products) {
        products.stream()
                .collect(Collectors.partitioningBy(product -> product.getPrice().compareTo(BigDecimal.valueOf(1000)) > 0))
                .forEach((key, value) -> System.out.println("Is premium? " + key + ". Products: " + value));
    }

    public static void highestPricedProduct(List<Product> products) {
        products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .ifPresent(System.out::println);
    }

    public static void checkPositiveRatingForProducts(List<Product> products) {
        boolean a = products.stream()
                .allMatch(product -> product.getRating() > 0);
        System.out.println("Czy produkty mają rating > 0? " + a);
    }

    public static void filteredByRatingAndPrice(List<Product> products) {
        products.stream()
                .filter(product -> product.getRating() >= 4.5 && product.getPrice().compareTo(BigDecimal.valueOf(500)) > 0)
                .sorted(Comparator.comparing(Product::getPrice))
                .forEach(System.out::println);
    }

    public static void totalRevenueAndAveragePriceInEachCategory(List<Product> products) {
        products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(), list -> {
                                    BigDecimal income = list.stream()
                                            .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getSold())))
                                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                                    BigDecimal average = list.stream()
                                            .map(Product::getPrice)
                                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                                            .divide(BigDecimal.valueOf(list.size()), 2, RoundingMode.HALF_UP);
                                    return Map.of("Income ", income, "Average price ", average);
                                }
                        )))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void numberOfProductsPerCategory(List<Product> products) {
        products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.counting()))
                .forEach(((key, value) -> System.out.println(key + " - ilość przedmiotów: " + value)));
    }

    public static void lowStockProductsSortedByStock(List<Product> products) {
        products.stream()
                .sorted(Comparator.comparing(Product::getStock))
                .filter(product -> product.getStock() < 10)
                .forEach(System.out::println);
    }

    public static void categoryWithHighestAverageRating(List<Product> products) {
        products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.averagingDouble(Product::getRating)))
                .entrySet()
                .stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .ifPresent(System.out::println);
    }

    public static void topFiveBySales(List<Product> products) {
        products.stream()
                .sorted(Comparator.comparing((Product product) -> product.getPrice().multiply(BigDecimal.valueOf(product.getSold())))
                        .reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}
