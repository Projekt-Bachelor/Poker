package de.tu_clausthal.in.bachelorproject2018.poker;

import java.util.ArrayList;

public class PlayerHand {
    private ArrayList<Card> handCards;
    private int[] handEvaluation;

    //Constructor
    public PlayerHand(){
        handCards = new ArrayList<Card>();
        handEvaluation = new int[6];
    }

    public ArrayList<Card> getHandCards(){
        return handCards;
    }
    public int[] getHandEvaluation(){
        return handEvaluation;
    }

    //give the player a card

    /**
     *
     * @param c
     * @return
     */
    public boolean takeCard(Card c){
        if (handCards.size() > 2){
            //too many handcards
            return false;
        } else {
            handCards.add(c);
        }
        return true;
    }

    //player shows his hand

    /**
     *
     * @return
     */
    public String showHand(){
        String output;
        //no need for loop, because player only has 2 valid handcards
        output = "Dieser Spieler hat: " + handCards.get(0).getValue() + " of "+ handCards.get(0).getSuit() +
                " und " + handCards.get(1).getValue() + " of "+ handCards.get(1).getSuit();
        return output;
    }

}
