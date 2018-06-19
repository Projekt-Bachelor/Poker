package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.Card;
import de.tu_clausthal.in.bachelorproject2018.poker.game.CardDealer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.PlayerHand;

public class CPrepareForWinCheck implements IWinCheckAction {

    @Override
    public IWinCheckAction apply( final PlayerHand p_playerHand )
    {
        //combine cards
        for (int i = 0; i<5; i++) {
            p_playerHand.getHandCards().add(CardDealer.getInstance().getTableCards().get(i));
        }
        //create rankarray
        for (Card card : p_playerHand.getHandCards()){
            p_playerHand.addRankArray(card.getValue());
        }

        return this;
    }
}
