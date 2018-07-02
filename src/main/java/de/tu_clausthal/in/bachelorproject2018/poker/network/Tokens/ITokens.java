package de.tu_clausthal.in.bachelorproject2018.poker.network.Tokens;

import org.springframework.lang.NonNull;

import javax.annotation.Nonnull;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Interface um die Tokens zu verwalten
 */
public interface ITokens {

    /**
     * Fügt einen neuen Token hinzu
     *
     * @param p_uuid UUID
     * @param p_table Tisch an dem der User spielt
     * @param p_player Spieler
     * @param timestamp Timestamp
     */
    @NonNull
    void add(@Nonnull final UUID p_uuid, @Nonnull final String p_table, @Nonnull final String p_player,
             @Nonnull final Timestamp timestamp);

    /**
     * Entfernt einen Token
     *
     * @param p_uuid UUID
     */
    void remove(@Nonnull final UUID p_uuid);
}
