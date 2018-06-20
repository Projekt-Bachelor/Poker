package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.Card;
import de.tu_clausthal.in.bachelorproject2018.poker.game.CardDealer;

public class CPrepareForWinCheck implements IWinCheckAction {

    @Override
    public IWinCheckAction apply(final HandStatistic handStatistic)
    {
        //combine cards
        for (int i = 0; i<5; i++) {
            handStatistic.getPlayer().getPlayerhand().getHandCards().add(CardDealer.getInstance().getTableCards().get(i));
        }
        //create rankarray
        for (Card card : handStatistic.getPlayer().getPlayerhand().getHandCards()){
            handStatistic.addRankArray(card.getValue());
        }

        return this;
    }
}
