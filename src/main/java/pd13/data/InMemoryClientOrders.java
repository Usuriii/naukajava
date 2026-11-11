package pd13.data;

import lombok.NoArgsConstructor;
import pd13.model.Client;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(staticName = "initialize")
public final class InMemoryClientOrders {
    private final List<Client> clientsOrder = new ArrayList<>();

    public List<Client> getClientsOrder() {
        return List.copyOf(clientsOrder);
    }

    public void save(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        clientsOrder.add(client);
    }
}
