package pd4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class Rental {
    private final Resources resources;
    private final int rentalDays;

    @Setter
    private RentalStatus rentalStatus = RentalStatus.ACTIVE;

    public double getTotalCost() {
        return resources.calculateTotalCost(rentalDays);
    }
}
