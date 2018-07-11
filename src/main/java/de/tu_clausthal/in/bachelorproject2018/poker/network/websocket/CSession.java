package de.tu_clausthal.in.bachelorproject2018.poker.network.websocket;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Nonnull;

/**
 * This object holds all important information to differentiate a session. The respective Websocket Session Id,
 * the table at which the player plays and the respective player object.
 */
public class CSession {

    /**
     * Session
     */
    private WebSocketSession m_session;

    /**
     *
     */
    private final String m_sessionId;

    /**
     * Tisch den der Spieler mit der jeweiligen SessionId bespielt
     */
    private final ITable m_table;

    /**
     * Der zur SessionId gehöhrende Spieler
     */
    private final IPlayer m_player;

    /**
     * Konstruktor
     * @param p_sessionId
     * @param p_table
     * @param p_player
     */
    public CSession(@Nonnull String  p_sessionId, @Nonnull ITable p_table, @Nonnull IPlayer p_player){
        m_sessionId = p_sessionId;
        m_table = p_table;
        m_player = p_player;
    }

    public String getSessionId(){
        return m_sessionId;
    }

    public ITable  getTable() {
        return m_table;
    }

    public IPlayer getPlayer() {
        return m_player;
    }

    public void setSession(@Nonnull WebSocketSession p_session){
        m_session = p_session;
    }

    public WebSocketSession getSession() {
        return m_session;
    }
}
