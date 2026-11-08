package pd11.data;

import lombok.RequiredArgsConstructor;
import pd11.model.Employee;
import pd11.model.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor(staticName = "initialize")
public final class InMemoryTicketRepository implements Repository<Ticket, Integer> {
    private final Map<Integer, Ticket> ticketMap = new HashMap<>();
    private final Map<Ticket, Employee> assignedTicketMap = new HashMap<>();
    private final Map<Ticket, String> closedDescriptionsMap = new HashMap<>();

    public Map<Integer, Ticket> getTicketMap() {
        return Map.copyOf(ticketMap);
    }

    public Map<Ticket, Employee> getAssignedTicketMap() {
        return Map.copyOf(assignedTicketMap);
    }

    public Map<Ticket, String> getClosedDescriptionsMap() {
        return Map.copyOf(closedDescriptionsMap);
    }

    @Override
    public void save(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        if (ticketMap.containsKey(ticket.getId())) {
            throw new IllegalArgumentException("Ticket with this ID already exist");
        }
        ticketMap.put(ticket.getId(), ticket);
    }

    @Override
    public Optional<Ticket> findById(Integer id) {
        return Optional.ofNullable(ticketMap.get(id));
    }

    public void saveAssigment(Ticket ticket, Employee employee) {
        assignedTicketMap.put(ticket, employee);
    }

    public void changeTicketExecutor(Ticket ticket, Employee employee) {
        assignedTicketMap.replace(ticket, employee);
    }

    public void saveClosedAssigmentDescription(Ticket ticket, String description) {
        closedDescriptionsMap.put(ticket, description);
    }

    public Optional<Employee> getAssigmentEmployee(Ticket ticket) {
        return Optional.ofNullable(assignedTicketMap.get(ticket));
    }
}
