package de.tu_clausthal.in.bachelorproject2018.poker.Websocket;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Each time a player closes the browser tab or loses the connection, that session is removed and the player
 * is removed from the table.
 */
@Component
public class StompDisconnectEvent implements ApplicationListener<SessionDisconnectEvent> {

    private final Log logger = LogFactory.getLog(StompDisconnectEvent.class);

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());

        logger.debug("Disconnect event [sessionId: " + sha.getSessionId() + "]");
        CSessionManagement.INSTANCE.remove(sha.getSessionId());
    }
}
