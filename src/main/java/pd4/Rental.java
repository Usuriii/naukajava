package pd4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Rental {
    private final Resources resources;
    private final int rentalDays;
    private RentalStatus rentalStatus = RentalStatus.ACTIVE;

    public double getTotalCost() {
        return resources.calculateTotalCost(rentalDays);
    }

    public void markAsReturned() {
        this.rentalStatus = RentalStatus.RETURNED;
    }
}
