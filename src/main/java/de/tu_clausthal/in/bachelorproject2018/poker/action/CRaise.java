package de.tu_clausthal.in.bachelorproject2018.poker.action;

import de.tu_clausthal.in.bachelorproject2018.poker.ChipsHandling;
import de.tu_clausthal.in.bachelorproject2018.poker.IPlayer;


public class CRaise implements IAction
{
    private int value;

    @Override
    public void accept( final IPlayer p_player )
    {
        p_player.amount( p_player.amount() + value );
        ChipsHandling.getInstance().
    }
}
