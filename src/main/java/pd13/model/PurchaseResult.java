package pd13.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PurchaseResult {
    private final Order order;
    private final int remainingQuantity;
}
