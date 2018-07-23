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

    /**
     *
     * @param event
     */
    @Override
    public void onApplicationEvent( final CMessageEvent event ) {
        switch (event.getMessage().getClass().getSimpleName().toLowerCase( Locale.ROOT )) {
            case "cgamemessage":
                final CGameMessage l_gameMessage = event.raw();
                event.getTable().list().forEach( i -> i.message( tomap( "text", l_gameMessage.getText() ) ) );
                return;

            case "ccardmessage":
                final CCardMessage l_cardMessage = event.raw();
                if (l_cardMessage.getDestination().equalsIgnoreCase("table"))
                    event.getTable().list().forEach( i -> i.message( new CCardJson(l_cardMessage.getCard(), l_cardMessage.getDestination(), l_cardMessage.getType() ) ) );
                else
                    l_cardMessage.getPlayer().message( new CCardJson( l_cardMessage.getCard(), l_cardMessage.getDestination(), l_cardMessage.getType() ) );
                return;

            case "cchipmessage":
                final CChipMessage l_chipMessage = event.raw();
                l_chipMessage.getPlayer().message( new CChipJson( l_chipMessage.getAmount() ) );
                return;

            case "cnotifymessage":
                final CNotifyMessage l_notifyMessage = event.raw();
                l_notifyMessage.getPlayer().message( new CNotifyJson( l_notifyMessage.getText() ) );
                return;

            default:
                throw new RuntimeException( "message type does not exist" );

        }
    }


    private static Map<String, Object> tomap( final String p_key, final Object p_value )
    {
        final Map<String, Object> l_map = new HashMap<>();
        l_map.put( p_key, p_value );
        return l_map;
    }
}
