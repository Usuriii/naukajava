package pd15.dto;

import java.time.LocalDate;
import pd15.domain.enums.LoanStatus;

public record LoanDto(
    Long id,
    BookDto bookDto,
    MemberDto memberDto,
    LocalDate borrowedAt,
    LocalDate dueDate,
    LocalDate returnedAt,
    LoanStatus loanStatus
) {}
