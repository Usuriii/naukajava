package pd13.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd13.model.Client;
import pd13.model.LaptopStore;

import java.util.concurrent.CompletableFuture;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseService {

    public static CompletableFuture<Integer> processPurchase(Client client, LaptopStore laptopStore) {
        return CompletableFuture.supplyAsync(() -> laptopStore.purchase(client));
    }
}
