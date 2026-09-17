package pd3;

public class PayPalProcessor implements PaymentProcessor {
    private static final double FEE = 0.01;

    @Override
    public PaymentStatus processPayment(double amount) {
        if (amount <= 0 ) {
            return PaymentStatus.FAILED;
        } else {
            System.out.println("Płatność paypal: " + amount + " PLN");
            return PaymentStatus.SUCCESS;
        }
    }

    @Override
    public PaymentStatus refund(double amount) {
        System.out.println("Zwrot płatności paypal: " + amount + " PLN");
        return PaymentStatus.REFUNDED;
    }

    @Override
    public double getTransactionFee() {
        return FEE;
    }

    @Override
    public String getName() {
        return "PayPal";
    }
}
