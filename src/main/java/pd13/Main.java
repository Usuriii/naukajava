package pd13;

import pd13.data.InMemoryClientOrders;
import pd13.model.Client;
import pd13.model.LaptopStore;
import pd13.printer.PurchasePrinter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Main {
    public static void main(String[] args) {
        LaptopStore store = new LaptopStore(10);
        var clientOrders = InMemoryClientOrders.initialize();

        clientOrders.save(new Client("Jan", 1));
        clientOrders.save(new Client("Maciek", 4));
        clientOrders.save(new Client("Kamil", 3));
        clientOrders.save(new Client("Zenek", 5));

        try {
            System.out.println("Store quantity: " + store.getAvailableQuantity());

//            PurchasePrinter.printPurchase(clientOrders.getClientsOrder().get(0), store)
//                    .join();
//
//            PurchasePrinter.printPurchase(clientOrders.getClientsOrder().get(1), store)
//                    .join();
//
//            PurchasePrinter.printPurchase(clientOrders.getClientsOrder().get(2), store)
//                    .join();
//
//            PurchasePrinter.printPurchase(clientOrders.getClientsOrder().get(3), store)
//                    .join();

            CompletableFuture.allOf(
                            PurchasePrinter.printPurchase(clientOrders.getClientsOrder().get(0), store),
                            PurchasePrinter.printPurchase(clientOrders.getClientsOrder().get(1), store),
                            PurchasePrinter.printPurchase(clientOrders.getClientsOrder().get(2), store),
                            PurchasePrinter.printPurchase(clientOrders.getClientsOrder().get(3), store))
                    .join();
        } catch (CompletionException e) {
            System.err.println("Rejected: " + e.getCause().getMessage());
        }
    }
}

