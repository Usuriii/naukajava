package pd15.dto;

import pd15.domain.enums.LoanStatus;

import java.time.LocalDate;

public record LoanDto(Long id,
                      BookDto bookDto,
                      MemberDto memberDto,
                      LocalDate borrowedAt,
                      LocalDate dueDate,
                      LocalDate returnedAt,
                      LoanStatus loanStatus) {
}
