package pd3.processor;

import pd3.model.PaymentStatus;

public interface PaymentProcessor {
    PaymentStatus processPayment(double amount);

    PaymentStatus refund(double amount);

    double getTransactionFee(double amount);

    String getName();
}
