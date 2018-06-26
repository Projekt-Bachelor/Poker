package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.tools.javac.util.Name;
import de.tu_clausthal.in.bachelorproject2018.poker.game.action.CFold;
import de.tu_clausthal.in.bachelorproject2018.poker.game.action.IAction;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.CSessionRegistration;
import de.tu_clausthal.in.bachelorproject2018.poker.network.EActions;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.websocket.CSession;
import de.tu_clausthal.in.bachelorproject2018.poker.websocket.ESessionManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.websocket.StompConnectEvent;
import de.tu_clausthal.in.bachelorproject2018.poker.game.action.CRaise;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Locale;
import java.util.Objects;


@Controller
public class CGameActionController {

    /**
     * Erhält das Raise-Objekt vom Client und ruft die .accept Funktion mit dem anhand der SessionId abstrahierten
     * Spiers auf
     * @param raise Raise Objekt mit dem Raise amount
     * @param headerAccessor
     */
    @MessageMapping("/game/action")
    public void raiseAction(final CMessage p_messagee, SimpMessageHeaderAccessor headerAccessor) {
        final ITable l_table = null; // Table aus Session
        final IPlayer l_player = null; // Player aus Session
        l_table.accept( p_messagee.setTable( l_table ).setPlayer( l_player ) );



        //raise.accept(ESessionManagement.INSTANCE.apply(headerAccessor.getSessionId()).getPlayer());
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


    public static final class CMessage implements IMessage
    {
        @JsonProperty( "type" )
        private String m_type;
        @JsonProperty( "value" )
        private Number m_value;
        private ITable m_table;
        private IPlayer m_player;

        @Override
        public IAction get()
        {
            switch ( m_type.toLowerCase( Locale.ROOT ) )
            {
                case "fold" :
                    return new CFold( m_table );

                default:
            }
        }

        public CMessage setTable( final ITable p_table )
        {
            m_table = Objects.nonNull( m_table ) ? m_table : p_table;
            return this;
        }

        public CMessage setPlayer( final IPlayer p_player )
        {
            m_player = p_player;
            return this;
        }

        @Override
        public IPlayer player()
        {
            return m_player;
        }
    }
}
