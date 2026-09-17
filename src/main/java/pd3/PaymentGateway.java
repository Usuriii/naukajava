package pd3;

import java.util.ArrayList;
import java.util.List;

public class PaymentGateway {
    private List<PaymentProcessor> paymentProcessor;
    private List<Transaction> transactionHistory = new ArrayList<>();

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
        PaymentProcessor bestProcessor = getBestProcessor();
        PaymentStatus paymentStatus = bestProcessor.processPayment(amount);
        Transaction transaction = new Transaction(amount, bestProcessor.getName(), paymentStatus);
        transactionHistory.add(transaction);
        return transaction;
    }

    public Transaction refund(double amount){
        validate(amount);
        PaymentProcessor bestProcessor = getBestProcessor();
        PaymentStatus refundStatus = bestProcessor.refund(amount);
        Transaction refundTransaction = new Transaction(amount, bestProcessor.getName(), refundStatus);
        transactionHistory.add(refundTransaction);
        return refundTransaction;
    }

    private PaymentProcessor getBestProcessor() {
        PaymentProcessor bestProcessor = paymentProcessor.get(0);
        double lowestFee = bestProcessor.getTransactionFee();
        for (PaymentProcessor processor : paymentProcessor) {
            double currentFee = processor.getTransactionFee();
            if (currentFee < lowestFee) {
                lowestFee = currentFee;
                bestProcessor = processor;
            }
        }
        return bestProcessor;
    }




}
