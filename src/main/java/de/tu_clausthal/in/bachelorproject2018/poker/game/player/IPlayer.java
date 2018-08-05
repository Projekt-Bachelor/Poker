package de.tu_clausthal.in.bachelorproject2018.poker.game.player;

import de.tu_clausthal.in.bachelorproject2018.poker.game.action.IAction;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import org.springframework.lang.NonNull;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.UUID;
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

    PlayerHand getPlayerhand();

    void addChips(int amount);

    int getChipsCount();

    boolean getAllIn();

    void playerAllIn();

    void resetAllIn();

    void fold();

    void check();

    boolean checkfolded();

    ITable getTable();

    void resetAmountBetThisRound();

    void resetHasChecked();

    void resetFolded();

    //int getChipsCount();

    boolean getChecked();

    /**
     * sets the custom Messaging Endpoint for each player!
     */
    void setMessagingEndpoint(@NonNull final UUID pUUID);

    /**
     * sends data
     *
     * @param p_data data objects
     * @return self-reference
     */
    IPlayer message( @Nonnull final Object... p_data );


    /**
     * setzt den aktuellen Betrag des Spielers
     *
     * @param p_amount Betrag
     * @return Objektreferenz
     */
    @NotNull
    IPlayer getAmountBetThisRound(@Nonnegative final int p_amount );

}
