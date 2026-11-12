package pd13.service;

import lombok.RequiredArgsConstructor;
import pd13.model.Order;
import pd13.model.LaptopStore;
import pd13.model.PurchaseResult;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor(staticName = "initialize")
public class PurchaseService {
    private final LaptopStore laptopStore;

    public CompletableFuture<PurchaseResult> processPurchase(Order order) {
        return CompletableFuture.supplyAsync(() -> {
            laptopStore.purchase(order);
            int remaining = laptopStore.getAvailableQuantity();
            return new PurchaseResult(order, remaining);
        });
    }
}
