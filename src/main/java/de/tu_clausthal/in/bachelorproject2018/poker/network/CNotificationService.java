package de.tu_clausthal.in.bachelorproject2018.poker.network;

import com.google.gson.Gson;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CCardMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CChipMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CGameMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CMessageEvent;
import org.pmw.tinylog.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;


@Service
public class CNotificationService implements ApplicationListener<CMessageEvent> {

    /**
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(CMessageEvent event) {

        Logger.info("Event with " + event.getMessage().getClass().getSimpleName());
        Gson gson = new Gson();

        try {
            switch (event.getMessage().getClass().getSimpleName()) {
                case "CGameMessage":
                    CGameMessage l_gameMessage = (CGameMessage) event.getMessage();
                    for (IPlayer l_player : event.getTable().list()) {
                        Logger.info(new TextMessage(l_gameMessage.getText()));
                        l_player.getSession().sendMessage(
                                new TextMessage(gson.toJson(( l_gameMessage.getText()))));
                    }
                    return;

                case "CCardMessage":
                    CCardMessage l_cardMessage = (CCardMessage) event.getMessage();
                    l_cardMessage.getPlayer().getSession().sendMessage(
                            new TextMessage(gson.toJson( l_cardMessage)));
                    return;

                case "CChipMessage":
                    CChipMessage l_chipMessage = (CChipMessage) event.getMessage();
                    l_chipMessage.getPlayer().getSession().sendMessage(
                            new TextMessage(gson.toJson( l_chipMessage )));
                    return;
            }
        }catch (IOException e){
            Logger.error("IOException!");
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
