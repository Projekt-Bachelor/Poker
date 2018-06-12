package de.tu_clausthal.in.bachelorproject2018.poker.game.table;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

import javax.annotation.Nonnull;
import java.util.Iterator;


/**
 * Interface um einen Spieletisch darzustellen.
 * Der Tisch ist ein Iterator, der immer rund herum läuft, bis nur noch ein Spieler da ist
 */
public interface ITable extends Iterator<IPlayer>
{
    /**
     * Name des Tisches
     *
     * @return Tischname
     */
    @Nonnull
    String name();

    /**
     * überprüft, ob der übergebene Spieler aktiv setzen kann
     *
     * @param p_player Spielerobjekt
     * @return aktiv Flag
     */
    boolean isactive( @Nonnull final IPlayer p_player );

    /**
     * liefert das Spielerobjekt, wer aktuell an der Reihe ist
     *
     * @return Spielerobjekt
     */
    IPlayer active();

    /**
     * entfernt einen Spieler vom Tisch
     *
     * @param p_player Spielerobjekt das entfertn werden soll
     * @return Objektreferenz auf den Tisch
     */
    ITable kick( @Nonnull final IPlayer p_player );
}
