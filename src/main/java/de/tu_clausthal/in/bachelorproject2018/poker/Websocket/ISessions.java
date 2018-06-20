package de.tu_clausthal.in.bachelorproject2018.poker.Websocket;

import javax.annotation.Nonnull;

/**
 * Interface to manage all websocket sessions.
 */
public interface ISessions {

    /**
     * Add a new session.
     * @param session Session Objekt
     */
    @Nonnull
    void add( @Nonnull final CSession session);

    /**
     * Removes a session.
     * @param sessionId Welcher Spieler soll entfernt werden.
     */
    void remove( @Nonnull final String sessionId);
}
