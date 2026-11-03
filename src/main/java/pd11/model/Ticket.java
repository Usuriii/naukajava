package pd11.model;

import lombok.Getter;
import lombok.ToString;
import pd11.model.enums.TicketCategory;
import pd11.model.enums.TicketPriority;
import pd11.model.enums.TicketStatus;

import java.util.Objects;

@Getter
@ToString
public final class Ticket implements Comparable<Ticket>, Identifable<Integer> {
    private final Integer id;
    private final String title;
    private final String description;
    private final TicketCategory ticketsCategory;
    private TicketStatus ticketStatus;
    private final TicketPriority ticketPriority;

    public Ticket(Integer id, String title, String description, TicketCategory ticketsCategory, TicketPriority ticketPriority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ticketsCategory = ticketsCategory;
        this.ticketStatus = TicketStatus.NEW;
        this.ticketPriority = ticketPriority;
        ticketValidation();
    }

    public void setStatusToActive() {
        if (ticketStatus == TicketStatus.NEW) {
            ticketStatus = TicketStatus.ACTIVE;
        } else {
            throw new IllegalStateException("Ticket status can only change from NEW to ACTIVE");
        }
    }

    public void setStatusToClose() {
        if (ticketStatus == TicketStatus.ACTIVE) {
            ticketStatus = TicketStatus.CLOSED;
        } else {
            throw new IllegalStateException("Ticket status can only change from ACTIVE to CLOSED");
        }
    }

    private void ticketValidation() {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Tittle cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        if (ticketsCategory == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (ticketPriority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Ticket o) {
        return ticketPriority.compareTo(o.ticketPriority);
    }
}
