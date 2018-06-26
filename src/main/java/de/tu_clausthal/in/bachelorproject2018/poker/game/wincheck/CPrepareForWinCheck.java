package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.Card;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

public class CPrepareForWinCheck extends IBaseWinCheckAction {

    protected CPrepareForWinCheck(ITable p_table) {
        super(p_table);
    }

    @Override
    /**
     * kombiniert die Handkarten des Spielers mit den Tischkarten
     * befüllt das rankArray
     */
    public IWinCheckAction apply(final HandStatistic handStatistic)
    {
        //combine cards
        for (int i = 0; i<5; i++) {
            handStatistic.getPlayer().getPlayerhand().getHandCards().add(
                    m_table.getGameHub().getCardDealer().getTableCards().get(i));
        }
        //create rankarray
        for (Card card : handStatistic.getPlayer().getPlayerhand().getHandCards()){
            handStatistic.addRankArray(card.getValue());
        }

        return this;
    }
}
