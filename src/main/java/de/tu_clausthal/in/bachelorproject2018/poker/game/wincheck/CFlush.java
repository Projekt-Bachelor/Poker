package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.PlayerHand;

public class CFlush implements IWinCheckAction {

    @Override
    public IWinCheckAction apply( final PlayerHand p_playerHand )
    {
        return this;
    }
}
