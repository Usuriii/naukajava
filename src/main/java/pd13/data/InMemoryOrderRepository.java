package pd13.data;

import lombok.NoArgsConstructor;
import pd13.model.Order;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(staticName = "initialize")
public final class InMemoryOrderRepository {

    private final List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return List.copyOf(orders);
    }

    public void save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        orders.add(order);
    }
}
