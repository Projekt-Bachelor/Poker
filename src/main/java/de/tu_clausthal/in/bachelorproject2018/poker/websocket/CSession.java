package de.tu_clausthal.in.bachelorproject2018.poker.websocket;

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
    private final String sessionId;

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
     * @param sessionId
     * @param table
     * @param player
     */
    public CSession(@Nonnull String sessionId, @Nonnull ITable table, @Nonnull IPlayer player){
        this.sessionId = sessionId;
        this.table = table;
        this.player = player;
    }

    public String getSessionId() {
        return sessionId;
    }

    public ITable  getTable() {
        return table;
    }

    public IPlayer getPlayer() {
        return player;
    }
}
