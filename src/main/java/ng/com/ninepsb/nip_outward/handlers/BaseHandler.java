package ng.com.ninepsb.nip_outward.handlers;

import ng.com.ninepsb.nip_outward.dto.request.NipOutwardRequest;
import ng.com.ninepsb.nip_outward.dto.response.NipOutwardResponse;
import ng.com.ninepsb.nip_outward.enums.HandlerName;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseHandler {

    protected HandlerName name;
    public abstract NipOutwardResponse handle(NipOutwardRequest nipOutwardRequest, HttpServletRequest httpServletRequest);

    protected final void init(HandlerName name) {
        this.name = name;
        HandlerManager.registerHandler(name, this);
    }

    public HandlerName getName() {
        return name;
    }
}
