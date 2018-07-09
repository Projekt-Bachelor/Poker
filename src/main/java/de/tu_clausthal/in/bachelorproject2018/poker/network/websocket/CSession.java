package de.tu_clausthal.in.bachelorproject2018.poker.network.websocket;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

import javax.annotation.Nonnull;

/**
 * This object holds all important information to differentiate a session. The respective Websocket Session Id,
 * the table at which the player plays and the respective player object.
 */
public class CSession {

    /**
     * SessionId
     */
    private final String m_session;

    /**
     * Tisch den der Spieler mit der jeweiligen SessionId bespielt
     */
    private final ITable table;

    /**
     * Der zur SessionId gehöhrende Spieler
     */
    private final IPlayer player;

    /**
     * Konstruktor
     * @param p_session
     * @param table
     * @param player
     */
    public CSession(@Nonnull String  p_session, @Nonnull ITable table, @Nonnull IPlayer player){
        this.m_session = p_session;
        this.table = table;
        this.player = player;
    }

    public String getSessionId() {
        return m_session;
    }

    public ITable  getTable() {
        return table;
    }

    public IPlayer getPlayer() {
        return player;
    }
}
