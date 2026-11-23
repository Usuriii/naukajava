package pd15.repository;

import lombok.RequiredArgsConstructor;
import pd15.domain.Loan;
import pd15.domain.Member;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor(staticName = "initialize")
public class MemberRepository implements Repository<Member> {
    private final Map<Long, Member> members = new HashMap<>();
    private final AtomicLong id = new AtomicLong(0);

    public void register(Member member) {
        member.assignId(id.incrementAndGet());
        members.put(member.getId(), member);
    }

    public List<Loan> getActiveLoans(Long memberId) {
        return members.get(memberId).getLoans().stream()
                .filter(loan -> loan.getReturnedAt() == null)
                .toList();
    }

    public boolean existById(Long id) {
        return members.containsKey(id);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(members.get(memberId));
    }
}
