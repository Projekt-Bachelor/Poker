package de.tu_clausthal.in.bachelorproject2018.poker.Websocket;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

import javax.annotation.Nonnull;

/**
 * This object holds all important information to differentiate a session. The respective Websocket Session Id,
 * the table at which the player plays and the respective player object.
 */
public class CSession {

    private final String sessionId;

    private final String table;

    private final IPlayer player;
    /**
     * Konstruktor
     * @param sessionId
     * @param table
     * @param player
     */
    public CSession(@Nonnull String sessionId, @Nonnull String table, @Nonnull IPlayer player){
        this.sessionId = sessionId;
        this.table = table;
        this.player = player;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String  getTable() {
        return table;
    }

    public IPlayer getPlayer() {
        return player;
    }
}
