package pd6;

public enum HandSign {
    ROCK("kamień"),
    PAPER("papier"),
    SCISSORS("nożyczki");

    final String description;

    HandSign(String description) {
        this.description = description;
    }

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
