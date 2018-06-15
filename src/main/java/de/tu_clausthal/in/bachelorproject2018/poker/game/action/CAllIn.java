package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.ChipsHandling;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

import javax.annotation.Nonnull;


/**
 * All-In Aktion
 *
 */
public final class CAllIn implements IAction
{
    @Override
    public void accept( @Nonnull final IPlayer p_player )
    {
        int allInAmount = p_player.getChipsCount();
        //set allIn to true
        p_player.playerAllIn();
        //amountBetThisRound updaten
        p_player.addToAmountBetThisRound(allInAmount);
        p_player.substractChips(allInAmount);
        //Chips dem Pot hinzufügen
        ChipsHandling.getInstance().addToPot(allInAmount, p_player.getAmountBetThisRound());

    }
}
