package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

public class HandStatistic {

    private IPlayer player;
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

    public HandStatistic(IPlayer player){
        this.player = player;
        //int[0] indicates type of hand, int[1]-int[5] describes hand in further detail
        handEvaluation = new int[6];
        //int[2]-[14] count the occurences of a card with that value in the playerhand
        rankArray = new int[15];
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

    public IPlayer getPlayer(){
        return player;
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
     * getter for Rankarray
     * @return rankarray as int[]
     */
    public int[] getRankArray(){
        return rankArray;
    }

}
