package pd11.service;

import pd11.model.enums.TicketCategory;
import pd11.model.enums.TicketPriority;
import pd11.model.enums.TicketStatus;
import pd11.model.Ticket;

import java.util.function.Predicate;

public class TicketFilterMethods {
    public static Predicate<Ticket> byCategory(TicketCategory category) {
        return c -> c.getTicketsCategory() == category;
    }

    public static Predicate<Ticket> byStatus(TicketStatus status) {
        return c -> c.getTicketStatus() == status;
    }

    public static Predicate<Ticket> byPriority(TicketPriority priority) {
        return c -> c.getTicketPriority() == priority;
    }
}
