package pd13.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LaptopStore {
    private int availableQuantity;

    public synchronized int purchase(Client client) {
        int quantityToBuy = client.getQuantityToBuy();
        if (quantityToBuy > availableQuantity) {
            throw new IndexOutOfBoundsException(("Order " + client.getName()
                    + " - The requested quantity (" + quantityToBuy
                    + ") is unavailable or out of stock. Remaining: "
                    + availableQuantity));
        }
        availableQuantity -= quantityToBuy;
        return availableQuantity;
    }
}
