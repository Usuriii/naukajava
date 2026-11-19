package pd15.repository;

import lombok.RequiredArgsConstructor;
import pd15.domain.Book;

import java.util.*;

@RequiredArgsConstructor(staticName = "initialize")
public class BookRepository implements Repository<Book> {
    private final Map<Long, Book> books = new HashMap<>();

    public void add(Book book) {
        books.put(book.getId(), book);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return books.values().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    public List<Book> findAvailable() {
        return books.values().stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .toList();

    }

    public List<Book> search(String query) {
        String normalizedQuery = query.toLowerCase(Locale.ROOT);

        return books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase(Locale.ROOT).contains(normalizedQuery)
                        || book.getTitle().toLowerCase(Locale.ROOT).contains(normalizedQuery)
                        || book.getIsbn().toLowerCase(Locale.ROOT).contains(normalizedQuery))
                .toList();
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        return Optional.ofNullable(books.get(bookId));
    }
}
