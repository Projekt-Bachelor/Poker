package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.PlayerHand;

public class CFlush implements IWinCheckAction {
    @Override
    public IWinCheckAction get() {
        return this;
    }

    public IWinCheckAction get(PlayerHand playerHand){
        //flush evaluation

        //straigth flush evaluation
        return this;
    }
}
