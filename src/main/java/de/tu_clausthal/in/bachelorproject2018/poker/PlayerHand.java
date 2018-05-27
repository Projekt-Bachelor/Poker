package de.tu_clausthal.in.bachelorproject2018.poker;

import java.util.ArrayList;

public class PlayerHand {
    private ArrayList<Card> handCards;
    private int[] handEvaluation;

    /**
     * Handevaluation[0] indicates the kind of hand you have at the end:
     * 8: Straightflush
     * 7: four of a kind
     * 6: Full House
     * 5: Flush
     * 4: Straight
     * 3: Triple
     * 2: two pair
     * 1: pair
     * 0: high card
     * handEvaluation[1-5] are the other important cards to determine the winner (e.g. highcards, or the ranks of the pairs
     */

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
    public void setHandEvaluation(int index, int value){
        handEvaluation[index] = value;
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
