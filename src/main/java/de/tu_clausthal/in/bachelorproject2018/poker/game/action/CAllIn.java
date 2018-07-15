package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import org.pmw.tinylog.Logger;

import javax.annotation.Nonnull;


/**
 * All-In Aktion
 *
 */
public final class CAllIn extends IBaseAction
{
    public CAllIn(ITable p_table) {
        super(p_table);
    }

    @Override
    /**
     * setzt den Spieler AllIn
     * setzt die Chips in den Pot
     */
    public void accept( @Nonnull final IPlayer p_player )
    {
        int allInAmount = p_player.getChipsCount();
        //set allIn to true
        p_player.playerAllIn();
        //amountBetThisRound updaten
        p_player.addToAmountBetThisRound(allInAmount);
        p_player.substractChips(allInAmount);
        //Chips dem Pot hinzufügen
        m_table.getGameHub().getChipsHandler().addToPot(allInAmount, p_player.getAmountBetThisRound());
        Logger.info(p_player.getName() + " ist AllIn!");

    }
}
