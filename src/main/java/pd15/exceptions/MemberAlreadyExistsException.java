package pd15.exceptions;

public class MemberAlreadyExistsException extends RuntimeException {
    public MemberAlreadyExistsException(Long id) {
        super("Member with ID:" + id + " already exist");
    }
}
