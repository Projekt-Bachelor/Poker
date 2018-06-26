package de.tu_clausthal.in.bachelorproject2018.poker.game;

import java.util.ArrayList;

public class PlayerHand {
    private ArrayList<Card> handCards;


    /**
     * constructor
     */
    public PlayerHand(){
        handCards = new ArrayList<Card>();
    }

    /**
     * getter for handcards
     * @return handcards as ArrayList<Card>
     */
    public ArrayList<Card> getHandCards(){
        return handCards;
    }


    /**
     * clear the handcards at the end of the round
     */
    public void resethandCards(){
        handCards.clear();
    }

    /**
     * give the player a card
     * @param c as card
     * @return boolean if it worked
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



    /**
     * show handcards of the players
     * @return handcards as string
     */
    public String showHand(){
        String output;
        //no need for loop, because player only has 2 valid handcards
        output = "Dieser Spieler hat: " + handCards.get(0).getValue() + " of "+ handCards.get(0).getStringSuit() +
                " und " + handCards.get(1).getValue() + " of "+ handCards.get(1).getStringSuit();
        return output;
    }

}
