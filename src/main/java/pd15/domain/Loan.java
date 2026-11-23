package pd15.domain;


import lombok.Getter;
import lombok.ToString;
import pd15.domain.enums.LoanStatus;

import java.time.LocalDate;

@Getter
@ToString
public class Loan {
    private static final int MAX_LOAN_DURATION_DAYS = 14;
    private Long id;
    private final Book book;
    @ToString.Exclude
    private final Member member;
    private final LocalDate borrowedAt;
    private final LocalDate dueDate;
    private LocalDate returnedAt;
    private LoanStatus loanStatus;

    public Loan(Book book, Member member, LocalDate borrowedAt, LocalDate dueDate) {
        this.book = book;
        this.member = member;
        this.borrowedAt = borrowedAt;
        this.dueDate = dueDate.plusDays(MAX_LOAN_DURATION_DAYS);
        this.returnedAt = null;
        this.loanStatus = LoanStatus.ACTIVE;
    }

    public boolean isOverdue() {
        return dueDate.isBefore(LocalDate.now()) && returnedAt == null;
    }

    public void markAsOverdue() {
        if (isOverdue()) {
            loanStatus = LoanStatus.OVERDUE;
        }
    }

    public void returnLoan() {
        returnedAt = LocalDate.now();
        loanStatus = LoanStatus.RETURNED;
    }

    public void assignId(Long id) {
        this.id = id;
    }
}
