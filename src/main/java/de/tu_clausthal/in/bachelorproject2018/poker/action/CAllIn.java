package de.tu_clausthal.in.bachelorproject2018.poker.action;

import de.tu_clausthal.in.bachelorproject2018.poker.ChipsHandling;
import de.tu_clausthal.in.bachelorproject2018.poker.IPlayer;


public class CAllIn implements IAction
{
    @Override
    public void accept( final IPlayer p_player )
    {
        p_player.getAmoung();
    }
}
