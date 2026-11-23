package pd15.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd15.domain.Loan;
import pd15.dto.LoanDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoanMapper {

    public static LoanDto toDto(Loan loan) {
        return new LoanDto(
                loan.getId(),
                BookMapper.toDto(loan.getBook()),
                MemberMapper.toDto(loan.getMember()),
                loan.getBorrowedAt(),
                loan.getDueDate(),
                loan.getReturnedAt(),
                loan.getLoanStatus()
        );
    }
}
