package pd6.match;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter

public enum HandSign {
    ROCK("kamień"),
    PAPER("papier"),
    SCISSORS("nożyczki");

    private final String description;

    boolean canBeat(HandSign option) {
        if (this == option) {
            return false;
        }

        return switch (this) {
            case ROCK -> option == SCISSORS;
            case PAPER -> option == ROCK;
            case SCISSORS -> option == PAPER;
        };
    }
}
