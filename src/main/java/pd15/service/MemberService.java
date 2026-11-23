package pd15.service;

import pd15.domain.Loan;
import pd15.dto.CommandMemberRegisterRequest;
import pd15.dto.MemberDto;

import java.util.List;


public interface MemberService {

    MemberDto register(CommandMemberRegisterRequest request);

    MemberDto findById(Long memberId);

    List<Loan> getActiveLoans(Long memberId);
}
