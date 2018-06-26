package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

public class CCheck extends IBaseAction {
    protected CCheck(ITable p_table) {
        super(p_table);
    }

    @Override
    /**
     * überprüft ob der Spieler checken kann
     * setzt, dass der Spieler gecheckt hat
     */
    public void accept(IPlayer p_player) {
        if (m_table.getGameHub().getChipsHandler().getHighestBidThisRound() > 0){
            throw new RuntimeException( "Du kannst nicht checken, da schon was gesetzt wurde" );
        }
        p_player.check();
    }
}
