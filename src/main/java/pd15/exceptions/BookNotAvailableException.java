package pd15.exceptions;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(String isbn) {
        super("Book with ISBN " + isbn + " is not available");
    }
}
