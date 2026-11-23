package pd15.service;

import java.util.List;
import pd15.domain.Loan;
import pd15.dto.CommandLoanRequest;
import pd15.dto.LoanDto;

public interface LoanService {
    LoanDto borrow(CommandLoanRequest request);

    void returnBook(Long loanId);

    List<Loan> findOverdue();
}
