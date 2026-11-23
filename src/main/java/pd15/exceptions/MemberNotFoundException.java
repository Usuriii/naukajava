package pd15.exceptions;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Long id) {
        super("Member with ID: " + id + " already exist");
    }
}
