package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.ChipsHandling;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

import javax.annotation.Nonnull;


/**
 * Call-Aktion
 */
public class CCall implements IAction
{


    @Override
    public void accept( @Nonnull final IPlayer p_player )
    {
        int callAmount;
        // Überprüfung, ob der Spieler überhaupt callen kann
        if ( p_player.getAmountBetThisRound() - ChipsHandling.getInstance().getHighestBidThisRound() > 0 )
            throw new RuntimeException( "Spieler kannst nicht callen" );
        //Wert berechnen, wie viel der Spieler noch zum Callen bezahlen muss
        callAmount = ChipsHandling.getInstance().getHighestBidThisRound()-p_player.getAmountBetThisRound();
        //AmountBetThisRound updaten
        p_player.addToAmountBetThisRound(callAmount);
        p_player.substractChips(callAmount);
        //callAmount zum Pot hinzufügen
        ChipsHandling.getInstance().addToPot(callAmount, p_player.getAmountBetThisRound());
    }
}
