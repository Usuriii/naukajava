package pd15.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Cannot find book with ID " + id);
    }
}
