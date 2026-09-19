package pd4;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract sealed class Resources implements Comparable<Resources> permits HeavyMachinery, PowerTools {
    private final String id;
    private String name;
    private double basePrice;
    private final ResourceType resourceType;

    public abstract double calculateTotalCost(int days);

    @Override
    public int compareTo(Resources other) {
        return Double.compare(this.basePrice, other.basePrice);
    }
}

