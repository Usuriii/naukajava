package pd13.printer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd13.model.Client;
import pd13.model.LaptopStore;
import pd13.service.PurchaseService;

import java.util.concurrent.CompletableFuture;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchasePrinter {

    public static CompletableFuture<Void> printPurchase(Client client, LaptopStore laptopStore) {
        return PurchaseService.processPurchase(client, laptopStore)
                .thenAccept(value -> System.out.println("Kupiono " + client.getQuantityToBuy() + " laptopów. Pozostało na stanie " + value));
    }
}
