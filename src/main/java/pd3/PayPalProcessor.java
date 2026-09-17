package pd3;

public class PayPalProcessor implements PaymentProcessor {
    private static final double CONSTANT_FEE = 10.0;

    @Override
    public PaymentStatus processPayment(double amount) {
        System.out.println("Płatność paypal: " + amount + " PLN");
        return PaymentStatus.SUCCESS;
    }

    @Override
    public PaymentStatus refund(double amount) {
        System.out.println("Zwrot płatności paypal: " + amount + " PLN");
        return PaymentStatus.REFUNDED;
    }

    @Override
    public double getTransactionFee(double amount) {
        return CONSTANT_FEE;
    }

    @Override
    public String getName() {
        return "PayPal";
    }
}
