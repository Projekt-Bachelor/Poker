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
        // Überprüfung, ob der Spieler überhaupt callen kann
        if ( p_player.amount() - ChipsHandling.getInstance().getHighestBidThisRound() < 0 )
            throw new RuntimeException( "Spieler kannst nicht callen" );

        //ChipsHandling.getInstance().addToPot( ChipsHandling.getInstance().getHighestBidThisRound() ):
        p_player.amount( p_player.amount() - ChipsHandling.getInstance().getHighestBidThisRound() );
    }
}
