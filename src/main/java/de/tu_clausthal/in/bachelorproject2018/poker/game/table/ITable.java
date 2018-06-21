package de.tu_clausthal.in.bachelorproject2018.poker.game.table;

import de.tu_clausthal.in.bachelorproject2018.poker.game.GameHub;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.function.Consumer;


/**
 * Interface um einen Spieletisch darzustellen.
 * Der Tisch ist ein Iterator, der immer rund herum läuft, bis nur noch ein Spieler da ist
 */
public interface ITable extends Consumer<IMessage>
{
    /**
     * Name des Tisches
     *
     * @return Tischname
     */
    @Nonnull
    String name();

    /**
     * flag ob das Spiel gestartet wurde
     *
     * @return Spiel läuft aktuell
     */
    boolean isplaying();

    /**
     * startet das Spiel
     *
     * @param p_owner Spieler, der das SPiel startet
     * @return Objektreferenz
     */
    @Nonnull
    ITable start( @Nonnull IPlayer p_owner );

    /**
     * entfernt einen Spieler vom Tisch
     *
     * @param p_player Spielerobjekt das entfertn werden soll
     * @return Objektreferenz auf den Tisch
     */
    @Nonnull
    ITable leave( @Nonnull final IPlayer p_player );

    /**
     * ein Spieler joint dem Spiel, wenn es noch nicht läuft
     *
     * @param p_player Player
     * @return Objektreferenz
     */
    @Nonnull
    ITable join( @Nonnull final IPlayer p_player );

    /**
     * liefert alle Spieler des Tisches
     */
    Collection<IPlayer> list();

    GameHub getGameHub();

}
