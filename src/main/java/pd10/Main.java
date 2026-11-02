package pd10;

public class Main {
    public static void main(String[] args) {
        var repository = ProductRepository.initialize();

        System.out.println("Top 5 produktów według sprzedaży");
        ProductAnalyzer.topFiveBySales(repository.getProducts());
        System.out.println("Kategoria z najwyższą średnią oceną");
        ProductAnalyzer.categoryWithHighestAverageRating(repository.getProducts());
        System.out.println("Produkty z niskim stanem (stock < 10) posortowane rosnąco po stanie");
        ProductAnalyzer.lowStockProductsSortedByStock(repository.getProducts());
        System.out.println("Liczba produktów per kategoria");
        ProductAnalyzer.numberOfProductsPerCategory(repository.getProducts());
        System.out.println("Całkowity przychód i średnia cena w każdej kategorii");
        ProductAnalyzer.totalRevenueAndAveragePriceInEachCategory(repository.getProducts());
        System.out.println("Produkty z oceną >= 4.5 i ceną < 500 — lista posortowana po cenie");
        ProductAnalyzer.filteredByRatingAndPrice(repository.getProducts());
        System.out.println("Sprawdź czy WSZYSTKIE produkty mają rating > 0 (allMatch)");
        ProductAnalyzer.checkPositiveRatingForProducts(repository.getProducts());
        System.out.println("Znajdź produkt o najwyższej cenie (max + Optional)");
        ProductAnalyzer.highestPricedProduct(repository.getProducts());
        System.out.println("Partycjonuj na \"premium\" (cena > 1000) i \"standard\" — zlicz każdą grupę (partitionBy)");
        ProductAnalyzer.categorizationProductsIntoPremiumAndStandard(repository.getProducts());
        System.out.println("Wygeneruj raport tekstowy: jedno zdanie per kategoria z top produktem");
        ProductAnalyzer.reportWithTopProductpPerCategory(repository.getProducts());
    }
}
