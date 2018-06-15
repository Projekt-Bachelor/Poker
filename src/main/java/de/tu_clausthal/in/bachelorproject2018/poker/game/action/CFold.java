package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;


public class CFold implements IAction{

    @Override
    public void accept(IPlayer p_player) {
        p_player.fold();
    }
}
