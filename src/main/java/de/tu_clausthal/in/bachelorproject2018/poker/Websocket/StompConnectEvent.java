package de.tu_clausthal.in.bachelorproject2018.poker.Websocket;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.List;

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

        final String table = sha.getNativeHeader("table").get(0);
        final List<String> player = sha.getNativeHeader("player");

        logger.debug("Connect event [sessiopnId: " + sha.getSessionId() + "]");
        //ESessionManagement.INSTANCE.add(new CSession(
                //sha.getSessionId(), table, ETables.INSTANCE.apply(table).apply()

        //));
    }
}
