package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.cards.Card;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.EGamestateManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CCardMessage;
import org.pmw.tinylog.Logger;

import java.util.Queue;

/**
 * Turn ausführung
 */
public class CTurn extends IBaseRoundAction {

    protected CTurn(ITable p_table){
        super(p_table);
    }

    @Override
    public void accept(Queue<IRoundAction> iRoundActions, IMessage iMessage) {
    }

    /**
     * Legt eine Karte vom Deck auf den Tisch
     * @param iRoundActions
     * @return
     */
    @Override
    public Boolean apply(Queue<IRoundAction> iRoundActions) {

        Card l_card = m_table.getGameHub().getCardDealer().getDeck().removeTopCard();
        m_table.getGameHub().getCardDealer().getTableCards().add(l_card);

        EGamestateManagement.INSTANCE.apply(m_table.name()).addCardMessage(
                new CCardMessage(l_card, "table", "turn", m_table, null));

        m_table.getGameHub().getChipsHandler().resetRound();

        Logger.info("Turn ausgeführt");
        Logger.info(m_table.getGameHub().getCardDealer().getTableCards());

        return false;
    }
}
