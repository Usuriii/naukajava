package pd15.repository;

import lombok.RequiredArgsConstructor;
import pd15.domain.Book;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor(staticName = "initialize")
public class BookRepository implements Repository<Book> {
    private final Map<Long, Book> books = new HashMap<>();
    private final AtomicLong id = new AtomicLong(0);

    public void save(Book book) {
        book.assignId(id.incrementAndGet());
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
