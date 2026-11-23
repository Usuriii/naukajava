package pd15.exceptions;

public class LoanLimitExceededException extends RuntimeException {
    public LoanLimitExceededException(Long id) {
        super("Loan limit reached for member with ID: " + id);
    }
}
