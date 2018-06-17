package de.tu_clausthal.in.bachelorproject2018.poker.game;

import java.util.ArrayList;

public class PlayerHand {
    private ArrayList<Card> handCards;
    private int[] handEvaluation;
    private int[] rankArray;

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

    /**
     * constructor
     */
    public PlayerHand(){
        handCards = new ArrayList<Card>();
        handEvaluation = new int[6];
        rankArray = new int[15];
    }

    /**
     * getter for handcards
     * @return handcards as ArrayList<Card>
     */
    public ArrayList<Card> getHandCards(){
        return handCards;
    }

    /**
     * getter for handevaluation
     * @return handevaluation as int[]
     */
    public int[] getHandEvaluation(){
        return handEvaluation;
    }

    /**
     * set the handevaluation elements to a certain value, used by winevaluation
     * @param index as int
     * @param value as int
     */
    public void setHandEvaluation(int index, int value){
        handEvaluation[index] = value;
    }

    /**
     * adds 1 to the rankarray[index]
     * @param index
     */
    public void addRankArray(int index){
        rankArray[index]++;
    }

    /**
     * set the handevaluation to zeroes
     */
    public void resetHandEvaluation(){
        for (int i = 0; i< 6; i++){
            handEvaluation[i]= 0;
        }
    }

    /**
     * set the rankArray to zeroes
     */
    public void resetRankArray(){
        for (int i = 0; i<15; i++){
            rankArray[i]= 0;
        }
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
     * getter for Rankarray
     * @return rankarray as int[]
     */
    public int[] getRankArray(){
        return rankArray;
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
