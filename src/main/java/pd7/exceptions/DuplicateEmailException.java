package pd7.exceptions;

public class DuplicateEmailException extends RegistrationException {
    public DuplicateEmailException() {
        super("This email already exists, please change the email.");
    }
}
