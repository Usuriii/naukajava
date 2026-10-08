package pd4.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pd4.model.enums.RentalStatus;

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
