package pd13;

import pd13.data.InMemoryOrderRepository;
import pd13.model.Order;
import pd13.model.LaptopStore;
import pd13.printer.PurchasePrinter;
import pd13.service.PurchaseService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Main {
    public static void main(String[] args) {
        LaptopStore store = LaptopStore.initialize();
        var clientOrders = InMemoryOrderRepository.initialize();
        var printer = PurchasePrinter.initalize();
        var service = PurchaseService.initialize(store);

        clientOrders.save(new Order("Jan", 11));
        clientOrders.save(new Order("Maciek", 3));
        clientOrders.save(new Order("Kamil", 3));
        clientOrders.save(new Order("Zenek", 5));

        try {
            System.out.println("Store quantity: " + store.getAvailableQuantity());
            CompletableFuture.allOf(
                            service.processPurchase(clientOrders.getOrders().get(0)).thenAccept(printer::printPurchase),
                            service.processPurchase(clientOrders.getOrders().get(1)).thenAccept(printer::printPurchase),
                            service.processPurchase(clientOrders.getOrders().get(2)).thenAccept(printer::printPurchase),
                            service.processPurchase(clientOrders.getOrders().get(3)).thenAccept(printer::printPurchase))
                    .join();
        } catch (CompletionException e) {
            System.err.println("Rejected: " + e.getCause().getMessage());
        }
    }
}

