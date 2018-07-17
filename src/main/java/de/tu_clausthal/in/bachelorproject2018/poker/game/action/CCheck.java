package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.EGamestateManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CGameMessage;
import org.pmw.tinylog.Logger;


public class CCheck extends IBaseAction {

    public CCheck(ITable p_table) {
        super(p_table);
    }

    @Override
    /**
     * überprüft ob der Spieler checken kann
     * setzt, dass der Spieler gecheckt hat
     */
    public void accept(IPlayer p_player) {
        if (!m_table.getGameHub().getChipsHandler().getNewRound()){
            throw new RuntimeException( "Du kannst nicht checken, da schon was gesetzt wurde" );
        }
        p_player.check();

        Logger.info("Spieler: " + p_player.getName() + " hat gecheckt!");
        EGamestateManagement.INSTANCE.apply(m_table.name()).addGameMessage(
                new CGameMessage("Spieler: " + p_player.getName() + " hat gecheckt!", m_table));
    }
}
