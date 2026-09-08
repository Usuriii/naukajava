package pd2;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int NUMBER_OF_SCORES = 3;
    private String name;
    private List<Integer> scores;

    public Player(String name) {
        this.name = name;
        this.scores = new ArrayList<>();
    }

    public int getNUMBER_OF_SCORES() {
        return NUMBER_OF_SCORES;
    }

    /**
     * The method add value to the list
     *
     * @param score is the value added to the list
     */
    public void addScores(int score) {
        scores.add(score);
    }

    /**
     * The method takes three values from the list and sums them.
     *
     * @return Sum of the values in the list.
     */
    public int getSum() {
        int sum = 0;
        for (int i = 0; i < NUMBER_OF_SCORES; i++) {
            sum = sum + scores.get(i);
        }
        return sum;
    }

    /**
     * The method takes three values from the list, sums them and calculates the average.
     *
     * @return Average of the values in the list.
     */
    public double getAverage() {
        double sum = 0;
        for (int i = 0; i < NUMBER_OF_SCORES; i++) {
            sum = sum + scores.get(i);
        }
        return sum / NUMBER_OF_SCORES;
    }

    /**
     * The method iterates through three elements and selects the smallest value.
     *
     * @return Smallest value from list.
     */
    public int getMin() {
        int min = scores.get(0);
        for (int i = 0; i < NUMBER_OF_SCORES; i++) {
            if (scores.get(i) < min) {
                min = scores.get(i);
            }
        }
        return min;
    }

    /**
     * The method iterates through three elements and selects the largest value.
     *
     * @return Largest value from list.
     */
    public int getMax() {
        int max = scores.get(0);
        for (int i = 0; i < NUMBER_OF_SCORES; i++) {
            if (scores.get(i) > max) {
                max = scores.get(i);
            }
        }
        return max;
    }

    /**
     * The method print information about player.
     *
     * @return Name, result of: sum, average to two decimal places, min and max value from list.
     */
    public String playersInfo() {
        return name + ", suma: " + getSum() + ", średnia: " + String.format("%.2f", getAverage()) + ", najmniejsza wartość: "
                + getMin() + ", najwieksza wartosc: " + getMax();
    }
}
