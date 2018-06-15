package de.tu_clausthal.in.bachelorproject2018.poker.game.player;

import de.tu_clausthal.in.bachelorproject2018.poker.game.action.IAction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.function.Consumer;


/**
 * Spieler Interface.
 * Der Spieler ist ein Consumer von Aktionen
 */
public interface IPlayer extends Consumer<IAction>
{
    /**
     * liefert den Namen des Spielers
     *
     * @return Spielername
     */
    @Nonnull
    String getName();

    /**
     * liefert den aktuellen Betrag des Spielers dieser Runde
     *
     * @return Betrag
     */
    @Nonnegative
    int getAmountBetThisRound();

    boolean substractChips(int amount);

    @Nonnegative
    void addToAmountBetThisRound(int amount);

    int getChipsCount();

    boolean getAllIn();

    void playerAllIn();

    void resetAllIn();

    void fold();

    void check();

    /**
     * setzt den aktuellen Betrag des Spielers
     *
     * @param p_amount Betrag
     * @return Objektreferenz
     */
    @NotNull
    IPlayer getAmountBetThisRound(@Nonnegative final int p_amount );

}
