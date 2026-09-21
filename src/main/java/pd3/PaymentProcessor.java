package pd3;

public interface PaymentProcessor {
    PaymentStatus processPayment(double amount);

    PaymentStatus refund(double amount);

    double getTransactionFee(double amount);

    String getName();
}
