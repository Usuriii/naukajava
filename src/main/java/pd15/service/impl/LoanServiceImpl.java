package pd15.service.impl;

import lombok.RequiredArgsConstructor;
import pd15.domain.Book;
import pd15.domain.Loan;
import pd15.domain.Member;
import pd15.dto.CommandLoanRequest;
import pd15.dto.LoanDto;
import pd15.exceptions.*;
import pd15.mapper.LoanMapper;
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

    @Override
    public LoanDto borrow(CommandLoanRequest request) {
        Member member = memberRepo.findById(request.memberId()).orElseThrow(() -> new MemberNotFoundException(request.memberId()));
        Book book = bookRepo.findById(request.bookId()).orElseThrow(() -> new BookNotFoundException(request.bookId()));

        memberLoanLimitValidation(member);
        book.borrow();
        LocalDate now = LocalDate.now();
        Loan loan = new Loan(book, member, now, now);
        repository.save(loan);
        member.addLoan(loan);
        return LoanMapper.toDto(loan);
    }

    @Override
    public void returnBook(Long loanId) {
        Loan loan = repository.findById(loanId).orElseThrow(() -> new LoanNotFoundException(loanId));
        if (loan.getReturnedAt() != null) {
            throw new LoanAlreadyReturnedException(loanId);
        } else {
            loan.returnLoan();
            loan.getBook().returnBack();
        }
    }

    @Override
    public List<Loan> findOverdue() {
        return repository.findOverdue();
    }

    private void memberLoanLimitValidation(Member member) {
        if (!member.hasAvailableLoanLimit()) {
            throw new LoanLimitExceededException(member.getId());
        }
    }
}
