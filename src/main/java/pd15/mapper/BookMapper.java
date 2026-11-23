package pd15.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd15.domain.Book;
import pd15.dto.BookDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookMapper {

    public static BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getAvailableCopies()
        );
    }
}
