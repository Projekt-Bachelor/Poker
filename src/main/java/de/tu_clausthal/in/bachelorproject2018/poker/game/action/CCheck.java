package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.round.CBetRound;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.EGamestateManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CGameMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CNotifyMessage;
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
        try {
            if (!m_table.getGameHub().getChipsHandler().getNewRound()) {
                throw new RuntimeException("Du kannst nicht checken, da schon was gesetzt wurde");
            }
            p_player.check();

            Logger.info("Spieler: " + p_player.getName() + " hat gecheckt!");
            EGamestateManagement.INSTANCE.apply(m_table.name()).addGameMessage(
                    new CGameMessage("Spieler: " + p_player.getName() + " hat gecheckt!", m_table));

        } catch (RuntimeException e){
            EGamestateManagement.INSTANCE.apply(m_table.name()).addNotifyMessage(
                    new CNotifyMessage("Du kannst nicht checken, da schon was gesetzt wurde", m_table, p_player));
            m_table.getQueue().add(new CBetRound(m_table, p_player));
        }
        }

}
