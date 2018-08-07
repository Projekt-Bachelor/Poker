package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.round.CBetRound;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.EGamestateManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CGameMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CNotifyMessage;
import org.pmw.tinylog.Logger;

import javax.annotation.Nonnull;


/**
 * Raise Aktion
 */
public final class CRaise extends IBaseAction
{
    /**
     * Wert für einen Raise
     */
    private int raiseValue;

    public CRaise(ITable p_table, int p_raiseValue) {
        super(p_table);
        raiseValue = p_raiseValue;
    }

    private String player;

    private String table;

    @Override
    /**
     * Überprüft ob der Spieler raisen kann
     * Führt den Raise durch
     */
    public void accept( @Nonnull final IPlayer p_player )
    {
        try {
            // Überprüfung, ob der Spieler sich den Raise überhaupt leisten kann
            if (p_player.getChipsCount() - raiseValue < 0)
                throw new RuntimeException("Raise ist zu hoch");

            //amountBetThisRound updaten
            p_player.addToAmountBetThisRound(raiseValue);
            p_player.substractChips(raiseValue);
            Logger.info("Spieler: " + p_player.getName() + " hat um folgenden Wert geraiset " + raiseValue);

            EGamestateManagement.INSTANCE.apply(m_table.name()).addGameMessage(
                    new CGameMessage("Spieler: " + p_player.getName() + " hat um folgenden Wert geraiset " + raiseValue, m_table));

            //Chips dem Pot hinzufügen
            m_table.getGameHub().getChipsHandler().addToPot(raiseValue, p_player.getAmountBetThisRound());

            EGamestateManagement.INSTANCE.apply(m_table.name()).addGameMessage(
                    new CGameMessage("Der Pot beträgt jetzt " + m_table.getGameHub().getChipsHandler().getPot(), m_table));
        } catch (RuntimeException e){
            EGamestateManagement.INSTANCE.apply(m_table.name()).addNotifyMessage(
                    new CNotifyMessage("So kannst du nicht raisen. Kann es sein, dass du etwas zu viel setzen wolltest?", m_table, p_player));
            m_table.getQueue().add(new CBetRound(m_table, p_player));
        }
    }

    public int getValue() {
        return raiseValue;
    }
}
