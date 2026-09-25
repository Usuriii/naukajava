package pd4;

public final class PowerTools extends Resources {
    private static final double WEAR_AND_TEAR_FEE_PERCENTAGE = 0.1;

    public PowerTools(String id, String name, double basePrice, ResourceType resourceType) {
        super(id, name, basePrice, resourceType);
    }

    @Override
    public double calculateTotalCost(int days) {
        double baseCost = getBasePrice() * days;
        return (getBasePrice() * days) + (baseCost * WEAR_AND_TEAR_FEE_PERCENTAGE);
    }
}
