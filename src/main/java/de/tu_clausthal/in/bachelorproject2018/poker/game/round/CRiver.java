package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.cards.Card;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.EGamestateManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CCardMessage;
import org.pmw.tinylog.Logger;

import java.util.Queue;


/**
 * River-Ausführung
 */
public final class CRiver extends IBaseRoundAction
{
    protected CRiver(ITable p_table) {
        super(p_table);
    }

    @Override
    public void accept( final Queue<IRoundAction> p_roundactions, final IMessage p_message )
    {
    }

    /**
     * Legt eine Karte vom Deck auf den Tisch
     * @param p_p_roundactions
     * @return
     */
    @Override
    public Boolean apply( final Queue<IRoundAction> p_p_roundactions )
    {

        Card l_card = m_table.getGameHub().getCardDealer().getDeck().removeTopCard();
        m_table.getGameHub().getCardDealer().getTableCards().add(l_card);

        EGamestateManagement.INSTANCE.apply(m_table.name()).addCardMessage(
                new CCardMessage(l_card, "table", "river",  m_table, null));

        m_table.getGameHub().getChipsHandler().resetRound();

        Logger.info("River ausgeführt");
        Logger.info(m_table.getGameHub().getCardDealer().getTableCards());

        return false;
    }
}
