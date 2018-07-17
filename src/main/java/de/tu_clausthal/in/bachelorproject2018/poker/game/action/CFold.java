package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.EGamestateManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CGameMessage;
import org.pmw.tinylog.Logger;


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
        Logger.info("Spieler: " + p_player.getName() + " hat gefoldet!");

        EGamestateManagement.INSTANCE.apply(m_table.name()).addGameMessage(
                new CGameMessage("Spieler: " + p_player.getName() + " hat gefoldet und ist raus!", m_table));
    }
}
