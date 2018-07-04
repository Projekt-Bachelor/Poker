package de.tu_clausthal.in.bachelorproject2018.poker.network;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CNotificationService implements ApplicationListener<CGameInformationEvent> {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    /**
     * Beim auftreten eines Events wird die Nachricht innerhalb des Events an alle User des Tisches geschickt
     *
     * @param gameInformationEvent Event-Objekt
     */
    @Override
    public void onApplicationEvent(CGameInformationEvent gameInformationEvent) {
        ETables.INSTANCE.apply( gameInformationEvent.getTable() )
                        .list()
                        .forEach( i -> messagingTemplate.convertAndSendToUser(
                            // @todo hier benötigst Du die WebSocket Session zum senden
                            i.getSessionId(),
                            "/queue/gamestate",
                            gameInformationEvent.getMessage(),
                            createHeaders( i.getSessionId() )
                        ));
    }

    /**
     * Setzt die richtigen Header
     *
     * @param p_sessionId
     * @return Headers
     */
    private static MessageHeaders createHeaders(String p_sessionId){
        final SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(p_sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
