package pd4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var system = RentalSystem.initialization();
        RentalPrintInfo printInfo = new RentalPrintInfo();

        Resources impactDrill = new PowerTools("PT1", "Wiertarka udarowa", 50, ResourceType.POWER_TOOLS);
        Resources grinder = new PowerTools("PT2", "Szlifierrka kątowa", 40, ResourceType.POWER_TOOLS);
        Resources excavator = new HeavyMachinery("HM1", "Koparka z wysięgnikiem", 440, ResourceType.HEAVY_MACHINERY, 300);
        Resources concreteMixer = new HeavyMachinery("HM2", "Betoniarka", 380, ResourceType.HEAVY_MACHINERY, 400);

        List<Rental> rentals = List.of(
                new Rental(impactDrill, 2),
                new Rental(grinder, 3),
                new Rental(excavator, 7),
                new Rental(concreteMixer, 1)
        );

        for (Rental rental : rentals) {
            system.addRentals(rental);
        }

        system.changeStatusToReturned("HM1");

        RentalSummary summary = system.getRentalSummary();
        printInfo.printSummary(summary);

    }
}
