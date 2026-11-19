package pd15.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
public class Loan {
    private final Long id;
    private final Book book;
    @ToString.Exclude
    private final Member member;
    private final LocalDate borrowedAt;
    private final LocalDate dueDate;
    private LocalDate returnedAt;

    public boolean isOverdue() {
        return dueDate.isBefore(LocalDate.now()) && returnedAt == null;
    }

    public void returnLoan() {
        returnedAt = LocalDate.now();
    }
}
