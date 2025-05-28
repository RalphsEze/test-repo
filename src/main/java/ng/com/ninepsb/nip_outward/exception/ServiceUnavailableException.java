package ng.com.ninepsb.nip_outward.exception;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException() {
        super("Something went wrong. Please try again.");
    }

    public ServiceUnavailableException(String message) {
        super(message);
    }
}
