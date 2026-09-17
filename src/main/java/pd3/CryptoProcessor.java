package pd3;

public class CryptoProcessor implements PaymentProcessor {
    private static final double FEE = 0.02;

    @Override
    public PaymentStatus processPayment(double amount) {
        if (amount <= 0 ) {
            return PaymentStatus.FAILED;
        } else {
            System.out.println("Płatność krypto: " + amount + " PLN");
            return PaymentStatus.SUCCESS;
        }
    }

    @Override
    public PaymentStatus refund(double amount) {
        System.out.println("Zwrot płatności krypto: " + amount + " PLN");
        return PaymentStatus.REFUNDED;
    }

    @Override
    public double getTransactionFee() {
        return FEE;
    }

    @Override
    public String getName() {
        return "Crypto";
    }
}
