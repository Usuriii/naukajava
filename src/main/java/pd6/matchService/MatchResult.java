package pd6.matchService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pd6.participants.Participant;

import java.util.Optional;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class MatchResult<T extends Participant> {
    private final T firstParticipant;
    private final T secondParticipant;
    private final Optional<T> winner;
    private final HandSign p1Sign;
    private final HandSign p2Sign;

    @Override
    public String toString() {
        String winnerText = winner.isPresent() ? ("Zwycięża: " + winner.get()) : "Remis";
        return "Gracz " + firstParticipant + " pokazał " + p1Sign.getDescription() + " VS Gracz "
                + secondParticipant + " pokazał " + p2Sign.getDescription() + ".\n" +
                winnerText + "\n";
    }
}
