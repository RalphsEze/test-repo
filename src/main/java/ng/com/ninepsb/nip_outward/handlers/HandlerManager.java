package ng.com.ninepsb.nip_outward.handlers;

import ng.com.ninepsb.logger_lib.service.CustomLogger;
import ng.com.ninepsb.nip_outward.enums.HandlerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HandlerManager {

    private static final Logger log = LoggerFactory.getLogger(HandlerManager.class.getName());
    private static final Map<HandlerName, BaseHandler> HANDLERS = new ConcurrentHashMap<>();

    private HandlerManager() {}

    public static BaseHandler getHandler(HandlerName name) {
        return HANDLERS.get(name);
    }

    public static void registerHandler(HandlerName name, BaseHandler handler) {
        log.info(":::Registering handler {}", name);
        HANDLERS.put(name, handler);
    }

}
