package pd4;

public class RentalPrintInfo {

    public void printSummary(RentalSummary summary) {
        System.out.println("Typy wypożyczanych rzeczy: " + ResourceType.POWER_TOOLS + ", " + ResourceType.HEAVY_MACHINERY);
        System.out.println("Ilość wypożyczeń o statusie active: " + summary.numberOfActiveRental());
        System.out.println("Ilość wypożyczeń o statusie returned: " + summary.numberOfReturnedRental());
        System.out.println("Suma cen wypożyczeń: " + summary.totalRentalCost() + " PLN\n");
        System.out.println("Zasoby posortowane naturalnie według ceny bazowej:");
        summary.sortedByPrice()
                .forEach(resources -> System.out.println(resources.getName() + ": " + resources.getBasePrice()));
        System.out.println("\nZasoby posortowane według nazwy:");
        summary.sortedByName()
                .forEach(resources -> System.out.println(resources.getName() + ": " + resources.getBasePrice()));
    }
}
