package de.tu_clausthal.in.bachelorproject2018.poker.network.websocket;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Singleton to manage all websocket sessions.
 */
public enum ESessionManagement implements ISessions, Function<String, CSession> {

    INSTANCE;

    /**
     * Thread-safe map that contains the session id and the respective session object with table and player.
     */
    private Map<String, CSession> sessions = new ConcurrentHashMap<>();

    @Nonnull
    @Override
    public void add(@Nonnull CSession session) {
        if (sessions.containsKey(session.getSessionId() ) )
            throw new RuntimeException(MessageFormat.format("Session [{0}] existiert schon", session) );

        sessions.put(session.getSessionId(), session);
    }

    @Override
    public void remove(@Nonnull String sessionId) {
        //TODO - Verbindung mit der Spielelogik (PlayerObjekt aus der Session)
        ETables.INSTANCE.apply(sessions.get(sessionId).getTable().toString()).leave(sessions.get(sessionId).getPlayer());
        sessions.remove(sessionId);
    }

    /**
     * Returns the session object to the corresponding session id.
     * @param sessionId
     * @return Session Object
     */
    @Override
    public CSession apply(String sessionId) {
        final CSession session = sessions.get(sessionId);
        if (Objects.isNull(session)){
            throw new RuntimeException(MessageFormat.format(
                    "Session mit der Id [{0}] nicht gefunden", sessionId));
        }
        return session;
    }
}
