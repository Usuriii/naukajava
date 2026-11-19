package pd15.service.impl;

import lombok.RequiredArgsConstructor;
import pd15.domain.Loan;
import pd15.domain.Member;
import pd15.dto.MemberDto;
import pd15.exceptions.MemberAlreadyExistsException;
import pd15.exceptions.MemberNotFoundException;
import pd15.mapper.MemberMapper;
import pd15.repository.MemberRepository;
import pd15.service.MemberService;

import java.util.List;

@RequiredArgsConstructor(staticName = "initialize")
public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository;

    @Override
    public MemberDto register(Member member) {
        if (repository.getMembers().containsKey(member.getId())) {
            throw new MemberAlreadyExistsException("Member with this ID already exist");
        }
        repository.register(member);
        return MemberMapper.toDto(member);
    }

    @Override
    public MemberDto findById(Long memberId) {
        Member member = repository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("Member with this ID doesnt exist"));
        return MemberMapper.toDto(member);
    }

    @Override
    public List<Loan> getActiveLoans(Long memberId) {
        if (!repository.getMembers().containsKey(memberId))
            throw new MemberNotFoundException("Member with this ID doesnt exist");
        return repository.getActiveLoans(memberId);
    }
}
