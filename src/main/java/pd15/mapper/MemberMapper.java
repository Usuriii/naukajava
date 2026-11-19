package pd15.mapper;

import pd15.domain.Member;
import pd15.dto.MemberDto;

public class MemberMapper {

    public static MemberDto toDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
    }
}
