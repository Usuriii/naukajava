package pd15.exceptions;

public class LoanAlreadyReturnedException extends RuntimeException {
    public LoanAlreadyReturnedException(Long id) {
        super("Loan with ID " + id + " was already returned");
    }
}
