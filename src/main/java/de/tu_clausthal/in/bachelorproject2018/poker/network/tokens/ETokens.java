package de.tu_clausthal.in.bachelorproject2018.poker.network.tokens;

import org.javatuples.Triplet;

import javax.annotation.Nonnull;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Singleton um alle momentan aktiven Token zu verwalten
 * Liefert als Supplier alle gespeicherten UUIDs
 * Die Funktion liefert für eine gegebene UUID das passende Tripel<Tisch, Spieler, Timestamp>
 */
public enum ETokens implements ITokens, Supplier<Set<UUID>>, Function<UUID, Triplet<String, String, Timestamp>> {

    INSTANCE;

    /**
     * Thread-safe Map, die die UUID und das Tripel aus Tisch, Spieler und Timestamp verwaltet
     */
    private Map<UUID, Triplet<String, String, Timestamp>> m_tokenMap = new ConcurrentHashMap<>();

    @Nonnull
    @Override
    public Triplet<String, String, Timestamp> apply(UUID p_uuid) {
        final Triplet<String, String, Timestamp> l_triplet = m_tokenMap.get(p_uuid);
        if (Objects.isNull(l_triplet))
            throw new RuntimeException(MessageFormat.format("UUID [{0}] existiert nicht", p_uuid));

        return l_triplet;
    }

    @Override
    public UUID add(@Nonnull UUID p_uuid, @Nonnull String p_table, @Nonnull String p_player,
                    @Nonnull Timestamp p_timestamp) {

        if (m_tokenMap.containsKey(p_uuid))
            throw new RuntimeException(MessageFormat.format("UUID [{0}] existiert bereits", p_uuid));

        m_tokenMap.put(p_uuid, new Triplet<>(p_table, p_player, p_timestamp));

        return p_uuid;
    }

    @Override
    public void remove(@Nonnull UUID p_uuid) {
        m_tokenMap.remove(p_uuid);
    }

    @Override
    public Set<UUID> get() {
        return m_tokenMap.keySet();
    }
}
