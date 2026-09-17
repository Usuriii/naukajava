package pd3;

public class Transaction {
    private final double amount;
    private final String processorName;
    private final PaymentStatus status;


    public Transaction(double amount, String processorName, PaymentStatus status) {
        this.amount = amount;
        this.processorName = processorName;
        this.status = status;
    }

    public String getProcessorName() {
        return processorName;
    }

    @Override
    public String toString() {
        return "Transaction:"
                + " AMOUNT: " + amount + " PLN,"
                + " PAYMENT PROCESSOR: " + processorName + ","
                + " STATUS: " + status;
    }
}
