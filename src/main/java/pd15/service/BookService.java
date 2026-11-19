package pd15.service;

import pd15.domain.Book;
import pd15.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookDto addBook(Book book);

    Optional<BookDto> findByIsbn(String isbn);

    List<BookDto> findAvailable();

    List<BookDto> search(String query);
}
