package pd15.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import pd15.domain.Loan;
import pd15.domain.Member;
import pd15.dto.CommandMemberRegisterRequest;
import pd15.dto.MemberDto;
import pd15.exceptions.MemberAlreadyExistsException;
import pd15.exceptions.MemberNotFoundException;
import pd15.mapper.MemberMapper;
import pd15.repository.MemberRepository;
import pd15.service.MemberService;

@RequiredArgsConstructor(staticName = "initialize")
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    @Override
    public MemberDto register(CommandMemberRegisterRequest register) {
        Member member = new Member(register.name(), register.email());
        repository
            .findById(member.getId())
            .ifPresent(user -> {
                throw new MemberAlreadyExistsException(member.getId());
            });
        repository.register(member);
        return MemberMapper.toDto(member);
    }

    @Override
    public MemberDto findById(Long memberId) {
        Member member = repository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        return MemberMapper.toDto(member);
    }

    @Override
    public List<Loan> getActiveLoans(Long memberId) {
        if (!repository.existById(memberId)) throw new MemberNotFoundException(memberId);
        return repository.getActiveLoans(memberId);
    }
}
