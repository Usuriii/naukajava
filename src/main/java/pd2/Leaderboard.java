package pd2;

import java.util.*;

public class Leaderboard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int playersNumber = getPlayersNumber(scanner);

        List<Player> players = new ArrayList<>(playersNumber);

        for (int i = 0; i < playersNumber; i++) {
            System.out.println("Podaj imie gracza");
            String name = scanner.nextLine();
            Player player = new Player(name);
            for (int j = 0; j < player.getNUMBER_OF_SCORES(); j++) {
                int round = j + 1;
                System.out.println("Runda numer " + round + ". Podaj wynik: ");
                player.addScores(scanner.nextInt());
                scanner.nextLine();
            }
            players.add(player);
        }

        players.sort(Comparator.comparing(Player::getSum).reversed());

        int bestMaxScore = getBestMaxScore(playersNumber, players);
        printLeaderBoard(playersNumber, players, bestMaxScore);

        scanner.close();
    }

    /**
     * The method retrieves the number of players from the user
     * and ensures the value is within the valid range.
     *
     * @param scanner for retrieving data from the user.
     * @return number of players.
     */
    private static int getPlayersNumber(Scanner scanner) {
        System.out.println("Podaj liczbę graczy od 2 do 10");
        int playersNumber = scanner.nextInt();
        scanner.nextLine();
        if (playersNumber < 2 || playersNumber > 10) {
            throw new IndexOutOfBoundsException("Wprowadzono złą ilość graczy");
        } else {
            System.out.println("Dodano " + playersNumber + " graczy");
        }
        return playersNumber;
    }

    /**
     * The method displays information about the players and their scores,
     * and marks the player with the highest single score with star.
     *
     * @param playersNumber is the number of participating players.
     * @param players       is a list storing players.
     * @param bestMaxScore  is the best score among all players.
     */
    private static void printLeaderBoard(int playersNumber, List<Player> players, int bestMaxScore) {
        System.out.println("Leaderboard: ");
        for (int i = 0; i < playersNumber; i++) {
            int ranking = 1;
            if (players.get(i).getMax() == bestMaxScore) {
                System.out.println("Miejsce " + (ranking + i) + ": " + players.get(i).playersInfo() + " \u2605");
            } else {
                System.out.println("Miejsce " + (ranking + i) + ": " + players.get(i).playersInfo());
            }
        }
    }

    /**
     * The method iterates through the list, checks the players maximum scores and
     * overwrites the variable if the value is greater.
     *
     * @param playersNumber is the number of participating players.
     * @param players       is a list storing players.
     * @return The best score from the entire list.
     */
    private static int getBestMaxScore(int playersNumber, List<Player> players) {
        int bestMaxScore = 0;
        for (int i = 0; i < playersNumber; i++) {
            if (players.get(i).getMax() > bestMaxScore)
                bestMaxScore = players.get(i).getMax();
        }
        return bestMaxScore;
    }
}
