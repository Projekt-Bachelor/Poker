package de.tu_clausthal.in.bachelorproject2018.poker.network;

import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CMessageEvent;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class CNotificationService implements ApplicationListener<CMessageEvent> {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    /**
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(CMessageEvent event) {

        Logger.info("Event with " + event.getMessage().getClass().getSimpleName());

        switch (event.getMessage().getClass().getSimpleName()){
            case "CGameMessage":
                //TODO - Add Sending Operations
        }


        /*ETables.INSTANCE.apply( gameInformationEvent.getTable() )
                        .list()
                        .forEach( i -> messagingTemplate.convertAndSendToUser(
                            // @todo hier benötigst Du die WebSocket Session zum senden
                            i.getSessionId(),
                            "/queue/gamestate",
                            gameInformationEvent.getMessage(),
                            createHeaders( i.getSessionId() )
                        ));*/
    }


}
