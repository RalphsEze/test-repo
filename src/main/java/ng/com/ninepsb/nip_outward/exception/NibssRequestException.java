package ng.com.ninepsb.nip_outward.exception;

import ng.com.ninepsb.nip_outward.enums.HandlerName;

public class NibssRequestException extends RuntimeException {

    private HandlerName handlerName;
    public NibssRequestException(String message, HandlerName handlerName) {
        super(message);
        this.handlerName = handlerName;
    }

    public NibssRequestException(String message) {
        super(message);
    }
}
