package de.tu_clausthal.in.bachelorproject2018.poker.action;

import de.tu_clausthal.in.bachelorproject2018.poker.ChipsHandling;
import de.tu_clausthal.in.bachelorproject2018.poker.IPlayer;


public class CCall implements IAction
{

    @Override
    public void accept( final IPlayer p_player )
    {
        ChipsHandling.getInstance().addToPot( ChipsHandling.getInstance().getHighestBidThisRound() - p_player.amount() ):
    }
}
