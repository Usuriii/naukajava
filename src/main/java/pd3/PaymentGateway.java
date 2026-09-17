package pd3;

import java.util.ArrayList;
import java.util.List;

public class PaymentGateway {
    private final List<PaymentProcessor> paymentProcessor;
    private final List<Transaction> transactionHistory = new ArrayList<>();

    public PaymentGateway(List<PaymentProcessor> paymentProcessor) {
        this.paymentProcessor = new ArrayList<>(paymentProcessor);
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    private void validate(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(PaymentStatus.FAILED + " - Kwota musi być większa od 0!");
        }
    }

    public Transaction payment(double amount) {
        validate(amount);
        PaymentProcessor bestProcessor = getBestProcessor(amount);
        PaymentStatus paymentStatus = bestProcessor.processPayment(amount);
        Transaction transaction = new Transaction(amount, bestProcessor.getName(), paymentStatus);
        transactionHistory.add(transaction);
        return transaction;
    }

    public Transaction refund(double amount) {
        validate(amount);
        PaymentProcessor bestProcessor = getBestProcessor(amount);
        PaymentStatus refundStatus = bestProcessor.refund(amount);
        Transaction refundTransaction = new Transaction(amount, bestProcessor.getName(), refundStatus);
        transactionHistory.add(refundTransaction);
        return refundTransaction;
    }

    private PaymentProcessor getBestProcessor(double amount) {
        PaymentProcessor bestProcessor = paymentProcessor.get(0);
        double lowestFee = bestProcessor.getTransactionFee(amount);
        for (PaymentProcessor processor : paymentProcessor) {
            double currentFee = processor.getTransactionFee(amount);
            if (currentFee < lowestFee) {
                lowestFee = currentFee;
                bestProcessor = processor;
            }
        }
        return bestProcessor;
    }
}
