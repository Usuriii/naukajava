package pd11.model.enums;

import lombok.Getter;

@Getter
public enum TicketStatus {
    NEW("Nowe"),
    ACTIVE("Aktywne"),
    CLOSED("Zamknięte");

    private final String description;

    TicketStatus(String description) {
        this.description = description;
    }
}
