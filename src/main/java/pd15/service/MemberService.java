package pd15.service;

import java.util.List;
import pd15.domain.Loan;
import pd15.dto.CommandMemberRegisterRequest;
import pd15.dto.MemberDto;

public interface MemberService {
    MemberDto register(CommandMemberRegisterRequest request);

    MemberDto findById(Long memberId);

    List<Loan> getActiveLoans(Long memberId);
}
