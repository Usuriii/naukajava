package pd15.service;

import pd15.domain.Loan;
import pd15.domain.Member;
import pd15.dto.MemberDto;

import java.util.List;


public interface MemberService {

    MemberDto register(Member member);

    MemberDto findById(Long memberId);

    List<Loan> getActiveLoans(Long memberId);
}
