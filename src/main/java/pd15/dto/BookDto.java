package pd15.dto;

public record BookDto(Long id,
                      String isbn,
                      String title,
                      String author,
                      int year,
                      int availableCopies) {
}
