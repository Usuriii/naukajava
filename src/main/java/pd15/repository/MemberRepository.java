package pd15.repository;

import lombok.RequiredArgsConstructor;
import pd15.domain.Loan;
import pd15.domain.Member;

import java.util.*;

@RequiredArgsConstructor(staticName = "initialize")
public class MemberRepository implements Repository<Member> {
    private final Map<Long, Member> members = new HashMap<>();

    public Map<Long, Member> getMembers() {
        return Map.copyOf(members);
    }

    public void register(Member member) {
        members.put(member.getId(), member);
    }

    public List<Loan> getActiveLoans(Long memberId) {
        return members.get(memberId).getLoans().stream()
                .filter(loan -> loan.getReturnedAt() == null)
                .toList();
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(members.get(memberId));
    }
}
