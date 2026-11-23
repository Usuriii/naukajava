package pd15.service;

import java.util.List;
import java.util.Optional;
import pd15.domain.Book;
import pd15.dto.BookDto;
import pd15.dto.CommandBookRequest;

public interface BookService {
    BookDto addBook(CommandBookRequest request);

    Optional<BookDto> findByIsbn(String isbn);

    List<BookDto> findAvailable();

    List<BookDto> search(String query);
}
