package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.PlayerHand;

public class CStraight implements IWinCheckAction {
    @Override
    public IWinCheckAction get() {
        return this;
    }

    public IWinCheckAction get(PlayerHand playerHand){
        //straight evaluation
        return this;
    }
}
