package pd3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Transaction {
    private final double amount;
    @Getter
    private final String processorName;
    private final PaymentStatus status;

    @Override
    public String toString() {
        return "Transaction:"
                + " AMOUNT: " + amount + " PLN,"
                + " PAYMENT PROCESSOR: " + processorName + ","
                + " STATUS: " + status;
    }
}
