package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.CSessionRegistration;
import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.EActions;
import de.tu_clausthal.in.bachelorproject2018.poker.Websocket.CSession;
import de.tu_clausthal.in.bachelorproject2018.poker.Websocket.ESessionManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.Websocket.StompConnectEvent;
import de.tu_clausthal.in.bachelorproject2018.poker.game.action.CRaise;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class CGameActionController {

    @MessageMapping("/gameAction/raise")
    public void raiseAction(final CRaise raise, SimpMessageHeaderAccessor headerAccessor) {

        // http://www.sergialmar.com/2014/03/detect-websocket-connects-and-disconnects-in-spring-4/

        /*
          bei Server-Session Disconnect, Session holen (Map<Session, Map.Entry<String, String>> - Map mit Sessions und Table und Spieler)
          und über ETables.apply( tablename ).kick( player ) dann Spieler entfernen
         */

        raise.accept(ESessionManagement.INSTANCE.apply(headerAccessor.getSessionId()).getPlayer());
        System.out.println("Spieler x hat geraiset!");
        //TODO - GameInformation erstellen
    }

    @MessageMapping("/gameAction/action")
    public void action(final EActions action, SimpMessageHeaderAccessor headerAccessor) {

        IPlayer player = ESessionManagement.INSTANCE.apply(headerAccessor.getSessionId()).getPlayer();

        action.actionFactory().accept(player);
    }

    @MessageMapping("/sessionConnect")
    public void createUser(final CSessionRegistration registration, SimpMessageHeaderAccessor headerAccessor) {

        Log logger = LogFactory.getLog(StompConnectEvent.class);
        logger.debug("Combine sessionId: " + headerAccessor.getSessionId() + " with table " + registration.getTable()
        + " and player " + registration.getPlayer());

        IPlayer player = ETables.INSTANCE.apply(registration.getTable()).list()
                .stream().filter(i -> i.name().equals(registration.getPlayer())).findFirst().get();

        ESessionManagement.INSTANCE.add(new CSession(
                headerAccessor.getSessionId(),
                registration.getTable(),
                player));
    }
}
