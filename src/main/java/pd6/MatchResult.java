package pd6;

import lombok.Getter;

import java.util.Optional;

@Getter
public class MatchResult<T extends Participant> {
    private final T playerOne;
    private final T playerTwo;
    private final Optional<T> winner;
    private final HandSign p1Sign;
    private final HandSign p2Sign;

    private MatchResult(T playerOne, T playerTwo, Optional<T> winner, HandSign p1Sign, HandSign p2Sign) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.winner = winner;
        this.p1Sign = p1Sign;
        this.p2Sign = p2Sign;
    }

    public static <T extends Participant> MatchResult<T> of(T playerOne, T playerTwo, Optional<T> winner, HandSign p1Sign, HandSign p2Sign) {
        return new MatchResult<>(playerOne, playerTwo, winner, p1Sign, p2Sign);
    }

    @Override
    public String toString() {
        String winnerText = winner.isPresent() ? ("Zwycięża: " + winner.get()) : "Remis";
        return "Gracz " + playerOne + " pokazał " + p1Sign.description + " VS Gracz " + playerTwo + " pokazał " + p2Sign.description + ".\n" +
                winnerText + "\n";
    }
}
