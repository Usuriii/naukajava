package pd4;

public final class HeavyMachinery extends Resources {
    private final double fuelLevelAtPickupInL;
    private final double fuelCost = 8.23;

    public HeavyMachinery(String id, String name, double basePrice, ResourceType resourceType, double fuelLevelAtPickupInL) {
        super(id, name, basePrice, resourceType);
        this.fuelLevelAtPickupInL = fuelLevelAtPickupInL;
    }

    @Override
    public double calculateTotalCost(int days) {
        return (getBasePrice() * days) + (fuelLevelAtPickupInL * fuelCost);
    }
}
