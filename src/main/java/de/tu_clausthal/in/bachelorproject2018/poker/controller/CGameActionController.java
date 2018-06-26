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

    /**
     * Erhält das Raise-Objekt vom Client und ruft die .accept Funktion mit dem anhand der SessionId abstrahierten
     * Spiers auf
     * @param raise Raise Objekt mit dem Raise amount
     * @param headerAccessor
     */
    @MessageMapping("/gameAction/raise")
    public void raiseAction(final CRaise raise, SimpMessageHeaderAccessor headerAccessor) {

        raise.accept(ESessionManagement.INSTANCE.apply(headerAccessor.getSessionId()).getPlayer());
        //TODO - GameInformation erstellen
    }

    @MessageMapping("/gameAction/action")
    public void action(final EActions action, SimpMessageHeaderAccessor headerAccessor) {

        IPlayer player = ESessionManagement.INSTANCE.apply(headerAccessor.getSessionId()).getPlayer();

        //action.actionFactory().accept(player);
        //TODO - Passende GameInformation erstellen
    }

    /**
     * Diese Funktion verbindet die SessionId mit dem Spieler und Tisch
     * @param registration Enthält Tisch und Spieler
     * @param headerAccessor
     */
    @MessageMapping("/sessionConnect")
    public void createUser(final CSessionRegistration registration, SimpMessageHeaderAccessor headerAccessor) {

        Log logger = LogFactory.getLog(StompConnectEvent.class);
        logger.debug("Combine sessionId: " + headerAccessor.getSessionId() + " with table " + registration.getTable()
        + " and player " + registration.getPlayer());

        IPlayer player = ETables.INSTANCE.apply(registration.getTable()).list()
                .stream().filter(i -> i.getName().equalsIgnoreCase(registration.getPlayer())).findFirst().get();

        ESessionManagement.INSTANCE.add(new CSession(
                headerAccessor.getSessionId(),
                registration.getTable(),
                player));
    }
}
