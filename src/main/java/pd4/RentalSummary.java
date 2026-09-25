package pd4;

import java.util.List;

public record RentalSummary(
        int numberOfActiveRental,
        int numberOfReturnedRental,
        double totalRentalCost,
        List<Resources> sortedByPrice,
        List<Resources> sortedByName
) {
}
