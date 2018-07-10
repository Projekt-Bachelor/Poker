package de.tu_clausthal.in.bachelorproject2018.poker.network;

import com.google.gson.Gson;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CGameInformationEvent;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;


@Service
public class CNotificationService implements ApplicationListener<CGameInformationEvent> {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    /**
     * Beim auftreten eines Events wird die Nachricht innerhalb des Events an alle User des Tisches geschickt
     *
     * @param gameInformationEvent Event-Objekt
     */
    @Override
    public void onApplicationEvent(CGameInformationEvent gameInformationEvent) {

        CNotification l_notification = new CNotification(gameInformationEvent.getMessage());

        ETables.INSTANCE.apply(gameInformationEvent.getTable()).list()
                    .forEach(i -> {
                        try {
                            i.getSession().sendMessage(new TextMessage(new Gson().toJson(l_notification)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });


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
