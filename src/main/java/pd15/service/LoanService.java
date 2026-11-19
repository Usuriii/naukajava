package pd15.service;

import pd15.domain.Loan;

import java.util.List;

public interface LoanService {

    void borrow(Long memberId, Long bookId);

    void returnBook(Long loanId);

    List<Loan> findOverdue();
}
