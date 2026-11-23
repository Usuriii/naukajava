package pd15.domain;

import java.time.Year;
import lombok.Getter;
import lombok.ToString;
import pd15.exceptions.BookNotAvailableException;

@Getter
@ToString
public class Book {

    private Long id;
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private int availableCopies;

    public Book(String isbn, String title, String author, int year, int availableCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.availableCopies = availableCopies;
        validate();
    }

    public void borrow() {
        if (availableCopies <= 0) {
            throw new BookNotAvailableException(isbn);
        }
        availableCopies -= 1;
    }

    public void returnBack() {
        availableCopies += 1;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    private void validate() {
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
