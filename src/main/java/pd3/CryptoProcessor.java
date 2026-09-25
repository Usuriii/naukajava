package pd3;

public class CryptoProcessor implements PaymentProcessor {
    private static final double PERCENTAGE_FEE = 0.02;
    private static final double CONSTANT_FEE = 10;

    @Override
    public PaymentStatus processPayment(double amount) {
        System.out.println("Płatność krypto: " + amount + " PLN");
        return PaymentStatus.SUCCESS;
    }

    @Override
    public PaymentStatus refund(double amount) {
        System.out.println("Zwrot płatności krypto: " + amount + " PLN");
        return PaymentStatus.REFUNDED;
    }

    @Override
    public double getTransactionFee(double amount) {
        return amount * PERCENTAGE_FEE + CONSTANT_FEE;
    }

    @Override
    public String getName() {
        return "Crypto";
    }
}
