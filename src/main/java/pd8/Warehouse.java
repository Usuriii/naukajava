package pd8;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private final Map<Long, Product> inventory = new HashMap<>();
    private final Map<String, Set<Product>> byCategory = new HashMap<>();
    private final PriorityQueue<Product> lowStock = new PriorityQueue<>(Comparator.comparingInt(Product::getQuantity));

    public void addProduct(Product product) {
        if (product == null) {
            throw new NullPointerException("Product cannot be null");
        }
        if (inventory.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product with this ID already exists");
        }
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        inventory.put(product.getId(), product);
        byCategory.putIfAbsent(product.getCategory(), new HashSet<>());
        byCategory.get(product.getCategory()).add(product);

        if (product.getQuantity() < 5) {
            lowStock.add(product);
        }
    }

    public void removeProduct(long id) {
        if (!inventory.containsKey(id)) {
            throw new IllegalArgumentException("Product with this ID doesnt exist");
        }
        Product removedProductById = inventory.remove(id);
        Set<Product> categoryProducts = byCategory.get(removedProductById.getCategory());
        categoryProducts.remove(removedProductById);
        if (categoryProducts.isEmpty()) {
            byCategory.remove(removedProductById.getCategory());
        }
        lowStock.remove(removedProductById);
    }

    public void updateQuantity(long id, int newQuantity) {
        if (!inventory.containsKey(id)) {
            throw new IllegalArgumentException("Product with this ID doesnt exist");
        }
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        Product product = inventory.get(id);
        lowStock.remove(product);
        product.setQuantity(newQuantity);
        if (newQuantity < 5) {
            lowStock.add(product);
        }
    }

    public List<Product> findByCategory(String category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (!byCategory.containsKey(category)) {
            throw new IllegalArgumentException("This category doesnt exist");
        }
        return byCategory.get(category)
                .stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();
    }

    public List<Product> getLowStockReport() {
        return lowStock.stream()
                .sorted(Comparator.comparing(Product::getQuantity))
                .toList();
    }

    public Map<String, DoubleSummaryStatistics> getCategoryStats() {
        Map<String, DoubleSummaryStatistics> stats = new HashMap<>();
        for (Product value : inventory.values()) {
            String productCategory = value.getCategory();
            if (!stats.containsKey(productCategory)) {
                stats.put(productCategory, new DoubleSummaryStatistics());
            }
            stats.get(productCategory).accept(value.getPrice().doubleValue());
        }
        return stats;
    }

    public TreeMap<BigDecimal, List<Product>> exportSortedByPrice() {
        return inventory.values().stream()
                .collect(Collectors.groupingBy(
                        Product::getPrice,
                        TreeMap::new,
                        Collectors.toList()
                ));
    }
}
