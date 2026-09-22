package pd4;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor(staticName = "initialization")
public class RentalSystem {
    private final List<Rental> rentals = new ArrayList<>();

    int rentalsNumberBasedOnStatus(RentalStatus status) {
        int number = 0;
        for (int i = 0; i < rentals.size(); i++) {
            if (rentals.get(i).getRentalStatus() == status) {
                number++;
            }
        }
        return number;
    }

    double calculateSumOfRentalsPrice() {
        double totalSum = 0;
        for (int i = 0; i < rentals.size(); i++) {
            totalSum += rentals.get(i).getTotalCost();
        }
        return totalSum;
    }

    public void addRentals(Rental rental) {
        this.rentals.add(rental);
    }

    public void changeStatusToReturned(String idOfReturnedItem) {
        for (Rental rental : rentals) {
            if (rental.getResources().getId().equalsIgnoreCase(idOfReturnedItem)) {
                rental.markAsReturned();
            }
        }
    }

    public RentalSummary getRentalSummary() {
        int active = rentalsNumberBasedOnStatus(RentalStatus.ACTIVE);
        int returned = rentalsNumberBasedOnStatus(RentalStatus.RETURNED);
        double totalRentalCost = calculateSumOfRentalsPrice();

        List<Resources> resourcesList = rentals.stream()
                .map(Rental::getResources)
                .toList();

        List<Resources> sortedByPrice = resourcesList.stream()
                .sorted()
                .toList();

        List<Resources> sortedByName = resourcesList.stream()
                .sorted(Comparator.comparing(Resources::getName))
                .toList();

        return new RentalSummary(active, returned, totalRentalCost, sortedByPrice, sortedByName);
    }
}
