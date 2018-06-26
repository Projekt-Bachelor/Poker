package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;


public class CFold extends IBaseAction{

    public CFold(ITable p_table) {
        super(p_table);
    }

    @Override
    /**
     * setzt den Spieler auf fold
     */
    public void accept(IPlayer p_player) {
        p_player.fold();
    }
}
