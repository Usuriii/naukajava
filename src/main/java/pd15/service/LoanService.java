package pd15.service;

import pd15.domain.Loan;
import pd15.dto.CommandLoanRequest;
import pd15.dto.LoanDto;

import java.util.List;

public interface LoanService {

    LoanDto borrow(CommandLoanRequest request);

    void returnBook(Long loanId);

    List<Loan> findOverdue();
}
