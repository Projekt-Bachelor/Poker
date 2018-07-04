package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tu_clausthal.in.bachelorproject2018.poker.game.action.*;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.CGameControl;
import de.tu_clausthal.in.bachelorproject2018.poker.network.CGameInformationEvent;
import de.tu_clausthal.in.bachelorproject2018.poker.network.CSessionRegistration;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.Tokens.ETokens;
import de.tu_clausthal.in.bachelorproject2018.poker.websocket.CSession;
import de.tu_clausthal.in.bachelorproject2018.poker.websocket.ESessionManagement;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.Locale;
import java.util.Objects;


@Controller
public class CGameActionController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * Erhält ein Message-Objekt vom Client und reicht es an das Backend weiter
     * @param p_message Message Objekt
     * @param headerAccessor
     */
    @MessageMapping("/game/action")
    public void raiseAction(final CMessage p_message, SimpMessageHeaderAccessor headerAccessor) {
        // Table aus Session
        final ITable l_table = ESessionManagement.INSTANCE.apply(headerAccessor.getSessionId()).getTable();
        // Player aus Session
        final IPlayer l_player = ESessionManagement.INSTANCE.apply(headerAccessor.getSessionId()).getPlayer();
        //ruft entsprechendes Objekt auf
        l_table.accept( p_message.setTable( l_table ).setPlayer( l_player ) );

        CGameInformationEvent gameInformationEvent = new CGameInformationEvent(
                this, "ApplicationEvent", l_table.name());
    }

    /**
     * Diese Funktion verbindet die SessionId mit dem Spieler und Tisch
     * @param p_registration Enthält Security (UUID)-Token
     * @param headerAccessor
     */
    @MessageMapping("/sessionConnect")
    public void createUser(final CSessionRegistration p_registration, SimpMessageHeaderAccessor headerAccessor) {

        final Triplet<String, String, Timestamp> l_security = ETokens.INSTANCE.apply(p_registration.get());
        /*if (Objects.isNull(l_security))
            throw new RuntimeException(MessageFormat.format("Der Token [{0}] ist bereits abgelaufen"), p_registration.get());*/

        IPlayer l_player = ETables.INSTANCE.apply(l_security.getValue0()).list()
                .stream().filter(i -> i.getName().equalsIgnoreCase(l_security.getValue1())).findFirst().get();

        ITable l_table = ETables.INSTANCE.apply(l_security.getValue0());

        ESessionManagement.INSTANCE.add(new CSession(
                headerAccessor.getSessionId(),
                l_table,
                l_player));
    }

    @MessageMapping("/game/startgame")
    public void startGame(final CGameControl p_gameControl, SimpMessageHeaderAccessor headerAccessor) {
        final CSession session = ESessionManagement.INSTANCE.apply(headerAccessor.getSessionId());
        final ITable l_table = session.getTable();

        l_table.start(session.getPlayer());
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

                case "call" :
                    return new CCall(m_table);

                case "check" :
                    return new CCheck(m_table);

                case "all-in" :
                    return new CAllIn(m_table);

                case "raise":
                    return new CRaise(m_table, m_value.intValue());
                /*default:
                    return new RuntimeException(MessageFormat.format("Aktion: [{0}] nicht gefunden"), m_type);*/
            }

            return null;
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

        public CMessage setValue( final int p_value ){
            m_value = p_value;
            return this;
        }

        @Override
        public IPlayer player()
        {
            return m_player;
        }

        @Override
        public String type() {
            return m_type;
        }

        @Override
        public Number value() {
            return m_value;
        }
    }
}
