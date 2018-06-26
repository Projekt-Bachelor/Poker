package de.tu_clausthal.in.bachelorproject2018.poker.websocket;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * Each time a new user / websocket connects, the Session Id, including the player and the table,
 * is saved using the SessionConnectedEvent.
 */
@Component
public class StompConnectEvent implements ApplicationListener<SessionConnectedEvent> {

    private final Log logger = LogFactory.getLog(StompConnectEvent.class);


    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent){
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sessionConnectedEvent.getMessage());

        //TODO - Access Stomp Headers
        logger.debug("Connect event [sessiopnId: " + sha.getSessionId() + "]");
    }
}
