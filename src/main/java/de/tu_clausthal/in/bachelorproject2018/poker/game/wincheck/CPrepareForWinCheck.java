package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.Card;
import de.tu_clausthal.in.bachelorproject2018.poker.game.CardDealer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.PlayerHand;

public class CPrepareForWinCheck implements IWinCheckAction {
    @Override
    public IWinCheckAction get() {
        return this;
    }

    public IWinCheckAction get(PlayerHand playerHand){
        //combine cards
        for (int i = 0; i<5; i++) {
            playerHand.getHandCards().add(CardDealer.getInstance().getTableCards().get(i));
        }
        //create rankarray
        for (Card card : playerHand.getHandCards()){
            playerHand.addRankArray(card.getValue());
        }

        return this;
    }
}
