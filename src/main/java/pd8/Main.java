package pd8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        addProducts(warehouse);
        System.out.println("Wyszukiwanie po kategorii:");
        warehouse.findByCategory("Elektronika")
                .forEach(System.out::println);
        System.out.println("\nLista zasobów o niskim stanie:");
        warehouse.getLowStockReport()
                .forEach(System.out::println);
        warehouse.getCategoryStats()
                .forEach((category, stats) -> {
                    System.out.println("\nKategoria: " + category);
                    System.out.println("Ilość produktów: " + stats.getCount());
                    BigDecimal roundedAverage = BigDecimal.valueOf(stats.getAverage()).setScale(2, RoundingMode.HALF_UP);
                    System.out.println("Średnia cena produktów: " + roundedAverage);
                    System.out.println("Mnimalna cena produktu: " + stats.getMin());
                    System.out.println("Maksymalna cena produktu: " + stats.getMax());
                });
        System.out.println();
        warehouse.exportSortedByPrice().forEach((price, products) -> {
            System.out.print("Cena: " + price + "zł - ");
            products.forEach(System.out::println);
        });
        System.out.println("\nUsunięcie produktu np Laptopa");
        warehouse.removeProduct(15432L);
        warehouse.findByCategory("Elektronika")
                .forEach(System.out::println);
        System.out.println("\nZmiana ilości produktu np iphona przez co dołączy do listy zasobow o niskim stanie");
        warehouse.updateQuantity(2341L, 1);
        warehouse.getLowStockReport()
                .forEach(System.out::println);
    }

    private static void addProducts(Warehouse warehouse) {
        warehouse.addProduct(new Product(15432L, "Laptop Lenovo", "Elektronika",
                new BigDecimal("2999.00"), 5));
        warehouse.addProduct(new Product(2341L, "iPhone 16 Pro", "Elektronika",
                new BigDecimal("3999.99"), 12));
        warehouse.addProduct(new Product(354356L, "Mikrofon HyperX", "Elektronika",
                new BigDecimal("899.99"), 2));
        warehouse.addProduct(new Product(5423424L, "Głośnik JBL 3", "Elektronika",
                new BigDecimal("299.88"), 7));
        warehouse.addProduct(new Product(51137L, "LEGO editions Football", "Zabawki i gry",
                new BigDecimal("109.99"), 5));
        warehouse.addProduct(new Product(4644322L, "Hula hop", "Zabawki i gry",
                new BigDecimal("25.74"), 3));
        warehouse.addProduct(new Product(67632L, "Gra logiczna planszowa", "Zabawki i gry",
                new BigDecimal("55.85"), 2));
        warehouse.addProduct(new Product(843L, "Puzzle 3D Kwiaty Lilia", "Zabawki i gry",
                new BigDecimal("34.66"), 8));
        warehouse.addProduct(new Product(1921L, "Kukiryn G2", "Rowery i hulajnogi",
                new BigDecimal("2899.54"), 9));
        warehouse.addProduct(new Product(43210L, "Rower górski MTB INDIANA", "Rowery i hulajnogi",
                new BigDecimal("1799.99"), 3));
        warehouse.addProduct(new Product(19211L, "Kask rowerowy ROCKBROS TT", "Rowery i hulajnogi",
                new BigDecimal("189.98"), 4));
        warehouse.addProduct(new Product(12312L, "Bieżnia elektryczna S-Line", "Fitness i sport",
                new BigDecimal("1699.99"), 3));
        warehouse.addProduct(new Product(54313L, "Atlas HMS Tytan", "Fitness i sport",
                new BigDecimal("1749.99"), 1));
        warehouse.addProduct(new Product(76514L, "Ławka do ćwiczeń S-Line", "Fitness i sport",
                new BigDecimal("498.84"), 6));
        warehouse.addProduct(new Product(7615L, "Wyciąg górny i dolny GORILLA SPORTS", "Fitness i sport",
                new BigDecimal("799.98"), 1));
        warehouse.addProduct(new Product(54315L, "Zestaw hantli 1-50kg", "Fitness i sport",
                new BigDecimal("6799.99"), 1));
    }
}
