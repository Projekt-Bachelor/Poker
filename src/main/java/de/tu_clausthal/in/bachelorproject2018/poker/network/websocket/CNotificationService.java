package de.tu_clausthal.in.bachelorproject2018.poker.network.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CCardMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CChipMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CGameMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CNotifyMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CCardJson;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CChipJson;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CMessageEvent;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CNotifyJson;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Service
public class CNotificationService implements ApplicationListener<CMessageEvent> {


    private static final String TO = "/message";
    private static final ObjectMapper JSONMAPPER = new ObjectMapper();

    /**
     *
     * @param event
     */
    @Override
    public void onApplicationEvent( final CMessageEvent event ) {

        try {
            switch (event.getMessage().getClass().getSimpleName().toLowerCase( Locale.ROOT )) {
                case "cgamemessage":
                    final CGameMessage l_gameMessage = event.raw();
                    final String l_gamemessage = JSONMAPPER.writeValueAsString( tomap( "text", l_gameMessage.getText() ) );
                    event.getTable().list().forEach( i -> i.sendto( TO, l_gamemessage ) );
                    return;

                case "ccardmessage":
                    final CCardMessage l_cardMessage = event.raw();
                    if (l_cardMessage.getDestination().equalsIgnoreCase("table"))
                    {
                        final String l_cardmessage = JSONMAPPER.writeValueAsString( new CCardJson(l_cardMessage.getCard(), l_cardMessage.getDestination(), l_cardMessage.getType() ) );
                        event.getTable().list().forEach( i -> i.sendto( TO, l_cardMessage ) );
                    } else
                        l_cardMessage.getPlayer().sendto( TO, JSONMAPPER.writeValueAsString( new CCardJson(l_cardMessage.getCard(), l_cardMessage.getDestination(), l_cardMessage.getType() ) ) );
                    return;

                case "cchipmessage":
                    final CChipMessage l_chipMessage = event.raw();
                    l_chipMessage.getPlayer().sendto( TO, JSONMAPPER.writeValueAsString( new CChipJson( l_chipMessage.getAmount() ) ) );
                    return;

                case "cnotifymessage":
                    final CNotifyMessage l_notifyMessage = event.raw();
                    l_notifyMessage.getPlayer().sendto( TO, JSONMAPPER.writeValueAsString( new CNotifyJson(l_notifyMessage.getText() ) ) );
                    return;

                default:
                    throw new RuntimeException( "message type does not exist" );

            }

        } catch (IOException e){
            e.printStackTrace();
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


    private static Map<String, Object> tomap( final String p_key, final Object p_value )
    {
        final Map<String, Object> l_map = new HashMap<>();
        l_map.put( p_key, p_value );
        return l_map;
    }
}
