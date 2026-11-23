package pd15.repository;

import lombok.RequiredArgsConstructor;
import pd15.domain.Loan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor(staticName = "initialize")
public class LoanRepository implements Repository<Loan> {
    private final Map<Long, Loan> loans = new HashMap<>();
    private final AtomicLong id = new AtomicLong(0);

    public void save(Loan loan) {
        loan.assignId(id.incrementAndGet());
        loans.put(loan.getId(), loan);
    }

    public List<Loan> findOverdue() {
        return loans.values().stream()
                .filter(Loan::isOverdue)
                .toList();
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return Optional.ofNullable(loans.get(id));
    }
}
