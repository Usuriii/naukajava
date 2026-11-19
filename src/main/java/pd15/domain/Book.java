package pd15.domain;


import lombok.Getter;
import lombok.ToString;
import pd15.exceptions.BookNotAvailableException;

import java.time.Year;

@Getter
@ToString
public class Book {
    private final Long id;
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private int availableCopies;

    public Book(Long id, String isbn, String title, String author, int year, int availableCopies) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.availableCopies = availableCopies;
        bookValidate();
    }

    public void borrow() {
        if (availableCopies <= 0) {
            throw new BookNotAvailableException("No books available for loan");
        }
        availableCopies -= 1;
    }

    public void returnBack() {
        availableCopies += 1;
    }

    private void bookValidate() {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (isbn.isBlank()) {
            throw new IllegalArgumentException("Isbn cannot be blank");
        }
        if (author.isBlank() || title.isBlank()) {
            throw new IllegalArgumentException("Author or title cannot be blank");
        }
        if (year <= 0 || year > Year.now().getValue()) {
            throw new IllegalArgumentException("Year cannot be negative or in the future");
        }
        if (availableCopies < 0) {
            throw new IllegalArgumentException("Copies cannot be negative");
        }
    }
}
