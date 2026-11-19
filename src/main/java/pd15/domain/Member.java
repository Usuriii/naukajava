package pd15.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Getter
@ToString
public class Member {
    private static final String EMAIL_STRUCTURE = ("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private final Long id;
    private final String name;
    private final String email;
    private final List<Loan> loans = new ArrayList<>();

    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        memberValidate();
    }

    public boolean canBorrow() {
        long count = loans.stream()
                .filter(loan -> loan.getReturnedAt() == null)
                .count();
        return count < 3;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    private void memberValidate() {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
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
