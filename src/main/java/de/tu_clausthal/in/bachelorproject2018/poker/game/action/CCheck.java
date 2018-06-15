package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.ChipsHandling;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

public class CCheck implements IAction {
    @Override
    public void accept(IPlayer p_player) {
        if (ChipsHandling.getInstance().getHighestBidThisRound() > 0){
            throw new RuntimeException( "Du kannst nicht checken, da schon was gesetzt wurde" );
        }
        p_player.check();
    }
}
