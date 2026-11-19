package pd15.dto;

import java.time.LocalDate;

public record LoanDto(Long id,
                      BookDto bookDto,
                      MemberDto memberDto,
                      LocalDate borrowedAt,
                      LocalDate dueDate,
                      LocalDate returnedAt) {
}
