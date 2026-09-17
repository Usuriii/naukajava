package pd3;

public class CreditCardProcessor implements PaymentProcessor {
    private static final double FEE = 0.05;

    @Override
    public PaymentStatus processPayment(double amount) {
        if (amount <= 0 ) {
            return PaymentStatus.FAILED;
        } else {
            System.out.println("Płatność kartą: " + amount + " PLN");
            return PaymentStatus.SUCCESS;
        }
    }

    @Override
    public PaymentStatus refund(double amount) {
        System.out.println("Zwrot płatności kartą: " + amount + " PLN");
        return PaymentStatus.REFUNDED;
    }

    @Override
    public double getTransactionFee() {
        return FEE;
    }

    @Override
    public String getName() {
        return "CreditCard";
    }


}
