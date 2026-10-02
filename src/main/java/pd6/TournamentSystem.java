package pd6;

import java.util.*;
import java.util.stream.Collectors;

public class TournamentSystem<T extends Participant> {
    private final Random random = new Random();

    private final Map<T, Integer> participants = new HashMap<>();
    private final List<MatchResult> matchResults = new ArrayList<>();

    public static TournamentSystem<Participant> initialization() {
        return new TournamentSystem<>();
    }

    public void addParticipant(T participant) {
        participants.put(participant, 0);
    }

    public MatchResult<T> match(T playerOne, T playerTwo) {
        if (playerOne == null || playerTwo == null) {
            throw new IllegalArgumentException("Gracz nie może być nullem");
        }
        if (playerOne.equals(playerTwo)) {
            throw new IllegalArgumentException("Nie można rozegrać meczu sam ze sobą");
        }

        HandSign p1Sign = HandSign.values()[random.nextInt(3)];
        HandSign p2Sign = HandSign.values()[random.nextInt(3)];

        if (p1Sign == p2Sign) {
            return MatchResult.of(playerOne, playerTwo, Optional.empty(), p1Sign, p2Sign);
        } else if (p1Sign.canBeat(p2Sign)) {
            return MatchResult.of(playerOne, playerTwo, Optional.of(playerOne), p1Sign, p2Sign);
        } else {
            return MatchResult.of(playerOne, playerTwo, Optional.of(playerTwo), p1Sign, p2Sign);
        }
    }

    public void saveResult(MatchResult<T> result) {
        if (result == null) {
            throw new IllegalArgumentException("Wynik meczu nie może być nullem");
        }
        T playerOne = result.getPlayerOne();
        T playerTwo = result.getPlayerTwo();
        Optional<T> winner = result.getWinner();

        if (winner.isEmpty()) {
            addPointsToPlayer(playerOne, 1);
            addPointsToPlayer(playerTwo, 1);
        } else if (winner.get().equals(playerOne)) {
            addPointsToPlayer(playerOne, 3);
        } else if (winner.get().equals(playerTwo)) {
            addPointsToPlayer(playerTwo, 3);
        }

        matchResults.add(result);
    }

    private void addPointsToPlayer(T player, int pointsToAdd) {
        if (player == null) {
            throw new IllegalArgumentException("Dodany gracz nie może być nullem");
        }

        Integer getPoints = participants.get(player);
        participants.put(player, getPoints + pointsToAdd);
    }

    public List<Map.Entry<T, Integer>> getParticipantsSortedByPoints() {
        return List.copyOf(participants.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList());
    }

    public <T extends Participant> Map<T, Integer> getAllParticipantsOfType(Class<T> type) {
        return Map.copyOf(participants.entrySet()
                .stream()
                .filter(entry -> type.isInstance(entry.getKey()))
                .collect(Collectors.toMap(
                        entry -> type.cast(entry.getKey()),
                        Map.Entry::getValue
                )));
    }
}
