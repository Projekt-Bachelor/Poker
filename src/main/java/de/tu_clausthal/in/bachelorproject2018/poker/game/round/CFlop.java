package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;

import java.util.Queue;


/**
 * Flop Ausführung
 *
 *
 */
public final class CFlop extends IBaseRoundAction
{
    public CFlop(ITable p_table) {
        super(p_table);
    }

    @Override
    public void accept( final Queue<IRoundAction> p_roundactions, final IMessage p_message )
    {
    }

    @Override
    /**
     * legt 3 Karten vom Deck auf den Tisch
     */
    public Boolean apply( final Queue<IRoundAction> p_p_roundactions )
    {

        m_table.getGameHub().getCardDealer().getTableCards().add(
                m_table.getGameHub().getCardDealer().getDeck().removeTopCard());
        m_table.getGameHub().getCardDealer().getTableCards().add(
                m_table.getGameHub().getCardDealer().getDeck().removeTopCard());
        m_table.getGameHub().getCardDealer().getTableCards().add(
                m_table.getGameHub().getCardDealer().getDeck().removeTopCard());
        m_table.getGameHub().getChipsHandler().resetRound();
        return false;
    }
}
