package pd7.exceptions;

public class DuplicateEmailException extends RegistrationException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
