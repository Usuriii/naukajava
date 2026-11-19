package pd15.mapper;

import pd15.domain.Loan;
import pd15.dto.LoanDto;

public class LoanMapper {

    public static LoanDto toDto(Loan loan) {
        return new LoanDto(
                loan.getId(),
                BookMapper.toDto(loan.getBook()),
                MemberMapper.toDto(loan.getMember()),
                loan.getBorrowedAt(),
                loan.getDueDate(),
                loan.getReturnedAt()
        );
    }
}
