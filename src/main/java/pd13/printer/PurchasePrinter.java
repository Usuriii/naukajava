package pd13.printer;

import lombok.NoArgsConstructor;
import pd13.model.PurchaseResult;

@NoArgsConstructor(staticName = "initalize")
public final class PurchasePrinter {

    public void printPurchase(PurchaseResult result) {
        System.out.println("Kupiono " + result.getOrder().getQuantityToBuy() + " laptopów. Pozostało na stanie " + result.getRemainingQuantity());
    }
}
