package ng.com.ninepsb.nip_outward.exception;

public class ForbiddenUserException extends RuntimeException {
    public ForbiddenUserException() {
        super("Forbidden User");
    }
    public ForbiddenUserException(String message) {
        super(message);
    }
}
