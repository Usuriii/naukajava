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

    public void printRentalInfo() {
        System.out.println("Typy wypożyczanych rzeczy: " + ResourceType.POWER_TOOLS + ", " + ResourceType.HEAVY_MACHINERY);
        System.out.println("Ilość wypożyczeń o statusie active: " + rentalsNumberBasedOnStatus(RentalStatus.ACTIVE));
        System.out.println("Ilość wypożyczeń o statusie returned: " + rentalsNumberBasedOnStatus(RentalStatus.RETURNED));
        System.out.println("Suma cen wypożyczeń: " + calculateSumOfRentalsPrice() + " PLN\n");
        System.out.println("Zasoby posortowane naturalnie według ceny bazowej:");

        List<Resources> resourcesList = rentals.stream()
                .map(Rental::getResources)
                .toList();

        resourcesList.stream()
                .sorted()
                .forEach(resources -> System.out.println(resources.getName() + ": " + resources.getBasePrice()));

        System.out.println("\nZasoby posortowane według nazwy:");
        resourcesList.stream()
                .sorted(Comparator.comparing(Resources::getName))
                .forEach(resources -> System.out.println(resources.getName() + ": " + resources.getBasePrice()));
    }
}
