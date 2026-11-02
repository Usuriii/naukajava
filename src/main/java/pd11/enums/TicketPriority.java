package pd11.enums;

import lombok.Getter;

@Getter
public enum TicketPriority {
    HIGH("Wysoki"),
    MEDIUM("Średni"),
    LOW("Niski");

    private final String description;

    TicketPriority(String description) {
        this.description = description;
    }
}
