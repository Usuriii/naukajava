package pd11.service;

import lombok.RequiredArgsConstructor;
import pd11.data.InMemoryTicketRepository;
import pd11.enums.TicketPriority;
import pd11.enums.TicketStatus;
import pd11.model.Employee;
import pd11.model.Ticket;

import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor(staticName = "initialize")
public final class Report {
    private final InMemoryTicketRepository ticketRepository;

    private void ticketsNumberByStatus(Map<Integer, Ticket> ticketMap) {
        ticketMap.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Ticket::getTicketStatus,
                        Collectors.counting()))
                .forEach((key, value) -> System.out.println(key + " ilość zgłoszeń: " + value));
    }

    private void ticketsNumberByCategory(Map<Integer, Ticket> ticketMap) {
        ticketMap.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Ticket::getTicketsCategory,
                        Collectors.counting()))
                .forEach((key, value) -> System.out.println(key + " ilość zgłoszeń: " + value));
    }

    private void activeTicketAssignToEmployee(Map<Ticket, Employee> assignTicket) {
        assignTicket.entrySet()
                .stream()
                .filter(c -> c.getKey().getTicketStatus() == TicketStatus.ACTIVE)
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + " ilość zgłoszeń: " + v));
    }

    private void unassignedTicketWithHighPriority(Map<Integer, Ticket> ticketMap, Map<Ticket, Employee> employeeMap) {
        ticketMap.values()
                .stream()
                .filter(c -> c.getTicketPriority() == TicketPriority.HIGH)
                .filter(ticket -> !employeeMap.containsKey(ticket))
                .toList()
                .forEach(System.out::println);
    }

    private boolean isAllTicketClose(Map<Integer, Ticket> ticketMap) {
        return ticketMap.values()
                .stream()
                .allMatch(c -> c.getTicketStatus() == TicketStatus.CLOSED);
    }

    private void closedTicketHistory() {
        ticketRepository.getClosedDescriptionsMap()
                .forEach((k, v) -> System.out.println(k + " - " + v));
    }

    public void printReport() {
        System.out.println("Report: ");
        System.out.println("Tickets number by status");
        ticketsNumberByStatus(ticketRepository.getTicketMap());
        System.out.println("\nTicket number by category");
        ticketsNumberByCategory(ticketRepository.getTicketMap());
        System.out.println("\nActive tickets assign to employee");
        activeTicketAssignToEmployee(ticketRepository.getAssignedTicketMap());
        System.out.println("\nUnassigned tickets with high priority");
        unassignedTicketWithHighPriority(ticketRepository.getTicketMap(), ticketRepository.getAssignedTicketMap());
        System.out.println("\nClosed ticket history");
        closedTicketHistory();
        System.out.println("\nAre all ticket closed? " + (isAllTicketClose(ticketRepository.getTicketMap()) ? "Yes" : "No"));
    }
}

