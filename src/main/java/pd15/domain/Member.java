package pd15.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Member {

    private static final String EMAIL_STRUCTURE = ("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private Long id;
    private final String name;
    private final String email;
    private final List<Loan> loans = new ArrayList<>();

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        validate();
    }

    public boolean hasAvailableLoanLimit() {
        long count = loans.stream().filter(loan -> loan.getReturnedAt() == null).count();
        return count < 3;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void assignId(Long id) {
        this.id = id;
    }

    private void validate() {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
        if (!email.matches(EMAIL_STRUCTURE)) {
            throw new IllegalArgumentException("Email doesnt match required format");
        }
    }
}
