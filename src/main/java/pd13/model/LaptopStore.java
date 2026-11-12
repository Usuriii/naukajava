package pd13.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(staticName = "initialize")
public class LaptopStore {
    private int availableQuantity = 10;

    public synchronized int getAvailableQuantity() {
        return availableQuantity;
    }

    public synchronized void purchase(Order order) {
        int quantityToBuy = order.getQuantityToBuy();
        if (quantityToBuy > availableQuantity) {
            throw new IllegalStateException(("Order " + order.getName()
                    + " - The requested quantity (" + quantityToBuy
                    + ") is unavailable or out of stock. Remaining: "
                    + availableQuantity));
        }
        availableQuantity -= quantityToBuy;
    }
}
