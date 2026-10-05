package pd4.printer;

import pd4.rentals.Resources;

import java.util.List;

public record RentalSummary(
        int numberOfActiveRental,
        int numberOfReturnedRental,
        double totalRentalCost,
        List<Resources> sortedByPrice,
        List<Resources> sortedByName
) {
}
