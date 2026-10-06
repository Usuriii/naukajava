package pd4.cli;

import pd4.model.Resources;

import java.util.List;

public record RentalSummary(
        int numberOfActiveRental,
        int numberOfReturnedRental,
        double totalRentalCost,
        List<Resources> sortedByPrice,
        List<Resources> sortedByName
) {
}
