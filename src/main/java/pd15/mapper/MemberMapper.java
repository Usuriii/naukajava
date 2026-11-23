package pd15.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd15.domain.Member;
import pd15.dto.MemberDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberMapper {

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getName(), member.getEmail());
    }
}
