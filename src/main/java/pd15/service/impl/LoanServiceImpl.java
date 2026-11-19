package pd15.service.impl;

import lombok.RequiredArgsConstructor;
import pd15.domain.Book;
import pd15.domain.Loan;
import pd15.domain.Member;
import pd15.exceptions.*;
import pd15.repository.BookRepository;
import pd15.repository.LoanRepository;
import pd15.repository.MemberRepository;
import pd15.service.LoanService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor(staticName = "initialize")
public class LoanServiceImpl implements LoanService {
    private final LoanRepository repository;
    private final BookRepository bookRepo;
    private final MemberRepository memberRepo;
    private Long loanId = 0L;

    @Override
    public void borrow(Long memberId, Long bookId) {
        Member member = memberRepo.findById(memberId).orElseThrow(() -> new MemberNotFoundException("Member with this ID doesnt exist"));
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with this ID doesnt exist"));

        if (!member.canBorrow()) {
            throw new LoanLimitExceededException("Borrowing limit exceeded");
        }
        book.borrow();
        LocalDate now = LocalDate.now();
        loanId += 1;
        Loan loan = new Loan(loanId, book, member, now, now.plusDays(14), null);
        repository.add(loan);
        member.addLoan(loan);
    }

    @Override
    public void returnBook(Long loanId) {
        Loan loan = repository.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan not found"));
        if (loan.getReturnedAt() != null) {
            throw new LoanAlreadyReturnedException("Loan was returned");
        } else {
            loan.returnLoan();
            loan.getBook().returnBack();
        }
    }

    @Override
    public List<Loan> findOverdue() {
        return repository.findOverdue();
    }
}
