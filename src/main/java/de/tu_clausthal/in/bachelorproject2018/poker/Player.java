package de.tu_clausthal.in.bachelorproject2018.poker;

import com.sun.xml.internal.bind.v2.TODO;

public class Player {
    private PlayerHand playerhand;
    private int chipsCount;
    private boolean fold;
    private int amountBetThisRound;
    private ChipsHandling chipsHandler;
    private boolean hasCheckedThisRound= false;


    /**
     * constructor
     */
    public Player() {
        this.playerhand = new PlayerHand();
        fold = false;
        amountBetThisRound = 0;
        this.chipsHandler = ChipsHandling.getInstance();
    }

    /**
     * to add chips to the players chipscount
     * @param add
     */
    public void addChips(int add){
        chipsCount += add;
    }

    /**
     * check if the player has enough chips to bet, and then substract it from the chipscount
     * @param substract
     * @return boolean if the player had enough chips
     */
    public boolean substractChips(int substract){
        if (chipsCount >= substract){
            chipsCount = chipsCount - substract;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * reset the amount for the next round
     */
    public void resetAmountBetThisRound(){
        amountBetThisRound = 0;
    }

    /**
     * getter to be able to access the cards of the player
     * @return PlayerHand
     */
    public PlayerHand getPlayerhand(){
        return playerhand;
    }

    /**
     * getter for the chipsCount
     * @return chipsCount as integer
     */
    public int getChipsCount(){
        return chipsCount;
    }

    /**
     * getter for the amount bet this round
     * @return amountBetThisRound as integer
     */
    public int getAmountBetThisRound(){
        return amountBetThisRound;
    }

    /**
     * calculate the amount to call, then bet the chips
     */
    public void call(){
        int remainingAmount;
        remainingAmount = chipsHandler.getHighestBidThisRound() - amountBetThisRound;
        if (substractChips(remainingAmount)){
            amountBetThisRound += remainingAmount;
            chipsHandler.addToPot(remainingAmount, amountBetThisRound);
        }
    }

    /**
     * add the amount to the chips already bet this round, then bet the chips
     * only works if the amount raised, is higher than the highestBidThisRound
     * @param amount
     */
    public void raise(int amount){
        if (amount+amountBetThisRound > chipsHandler.getHighestBidThisRound()){
            if (substractChips(amount)) {
                amountBetThisRound += amount;
                chipsHandler.addToPot(amount, amountBetThisRound);
            }
        }
    }

    /**
     * check this round
     */
    public void check(){
        hasCheckedThisRound = true;
    }

    /**
     * reset hasChecked, to be able to check next round
     */
    public void resetHasChecked(){
        hasCheckedThisRound = false;
    }

    /**
     * getter for hasChecked
     * @return hasCheckedThisRound as boolean
     */
    public boolean getHasCheckedThisRound(){
        return hasCheckedThisRound;
    }

    /**
     * fold this round, count down the counter of players still in this round
     */
    public void fold(){
        chipsHandler.somebodyHasFolded();
        fold = true;
    }

    /**
     * check if the player has folded
     * @return folded as boolean
     */
    public boolean checkFolded(){
        return fold;
    }

    /**
     * reset folded for the next round
     */
    public void resetFolded(){
        fold = false;
    }


    //Issue for Niklas to complete Method
    public void checkAction(){

    }


}
