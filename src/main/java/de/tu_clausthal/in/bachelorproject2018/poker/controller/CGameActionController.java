package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tu_clausthal.in.bachelorproject2018.poker.game.action.*;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CGameControl;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CSessionRegistration;
import de.tu_clausthal.in.bachelorproject2018.poker.network.tokens.ETokens;
import de.tu_clausthal.in.bachelorproject2018.poker.network.websocket.CSession;
import de.tu_clausthal.in.bachelorproject2018.poker.network.websocket.ESessionManagement;
import org.javatuples.Triplet;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.Locale;
import java.util.Objects;


@Controller
public class CGameActionController {

    /**
     * Erhält ein Message-Objekt vom Client und reicht es an das Backend weiter
     * @param p_message Message Objekt
     * @param headerAccessor
     */
    @MessageMapping("/game/action")
    public void gameAction(final CMessage p_message, SimpMessageHeaderAccessor headerAccessor) {
        // Table aus Session
        final ITable l_table = ESessionManagement.INSTANCE.apply(headerAccessor.getSessionId()).getTable();
        // Player aus Session
        final IPlayer l_player = ESessionManagement.INSTANCE.apply(headerAccessor.getSessionId()).getPlayer();

        //ruft entsprechendes Objekt auf
        l_table.accept( p_message.setTable( l_table ).setPlayer( l_player ) );

    }

    @MessageMapping("/sessionConnect")
    public void createUser(final CSessionRegistration p_registration, SimpMessageHeaderAccessor headerAccessor) {

        final Triplet<String, String, Timestamp> l_security = ETokens.INSTANCE.apply(p_registration.get());

        ITable l_table = ETables.INSTANCE.apply(l_security.getValue0());

        IPlayer l_player = l_table.list().stream()
                .filter(i -> i.getName().equalsIgnoreCase(l_security.getValue1())).findFirst().get();

        l_player.setMessagingEndpoint(p_registration.get());

        ESessionManagement.INSTANCE.add(new CSession(headerAccessor.getSessionId(), l_table, l_player));
    }

    @MessageMapping("/game/controls")
    public void startGame(final CGameControl p_gameControl, SimpMessageHeaderAccessor headerAccessor) {
        final CSession session = ESessionManagement.INSTANCE.apply(headerAccessor.getSessionId());
        final ITable l_table = session.getTable();

        switch (p_gameControl.getAction()) {
            case "startgame":
                l_table.start(session.getPlayer());
                return;

            case "leave":
                l_table.leave(session.getPlayer());
                return;
        }
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
        public String type() {
            return m_type;
        }

        @Override
        public Number value() {
            return m_value;
        }

        @Override
        public ITable table() {
            return m_table;
        }

        @Override
        public IPlayer player()
        {
            return m_player;
        }
    }
}
