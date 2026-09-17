package pd3;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<PaymentProcessor> processors = new ArrayList<>();
        processors.add(new CreditCardProcessor());
        processors.add(new PayPalProcessor());
        processors.add(new CryptoProcessor());

        PaymentGateway paymentGateway = new PaymentGateway(processors);

        Transaction payment = paymentGateway.payment(220);
        System.out.println(payment.getProcessorName() + payment);

        Transaction refund = paymentGateway.refund(200);
        System.out.println(payment.getProcessorName() + refund);


        System.out.println("Historia transakcji:");
        for (Transaction transaction : paymentGateway.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}
