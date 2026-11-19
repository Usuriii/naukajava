package pd15.service.impl;

import lombok.RequiredArgsConstructor;
import pd15.domain.Book;
import pd15.dto.BookDto;
import pd15.mapper.BookMapper;
import pd15.repository.BookRepository;
import pd15.service.BookService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(staticName = "initialize")
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Override
    public BookDto addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        if (repository.findById(book.getId()).isPresent()) {
            throw new IllegalArgumentException("Book with this ID already exist");
        }
        if (repository.findById(book.getId()).isPresent()) {
            throw new IllegalArgumentException("Book with this ISBN already exist");
        }
        repository.add(book);
        return BookMapper.toDto(book);
    }

    @Override
    public Optional<BookDto> findByIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("Isbn cannot be null or blank");
        }
        return repository.findByIsbn(isbn)
                .map(BookMapper::toDto);
    }

    @Override
    public List<BookDto> findAvailable() {
        return repository.findAvailable().stream()
                .map(BookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDto> search(String query) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("Query cannot be null or blank");
        }
        return repository.search(query).stream()
                .map(BookMapper::toDto)
                .toList();
    }
}
