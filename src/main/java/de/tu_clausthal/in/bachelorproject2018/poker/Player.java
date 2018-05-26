package de.tu_clausthal.in.bachelorproject2018.poker;

import com.sun.xml.internal.bind.v2.TODO;

public class Player {
    private PlayerHand playerhand;
    private int chipsCount;
    private boolean fold;
    private int amountBetThisRound;
    private ChipsHandling chipsHandler;
    private boolean hasCheckedThisRound= false;


    //contructor
    public Player(ChipsHandling chipsHandler) {
        this.playerhand = new PlayerHand();
        fold = false;
        amountBetThisRound = 0;
        this.chipsHandler = chipsHandler;
    }
    //to add chips
    public void addChips(int add){
        chipsCount += add;
    }
    //to substract chips for betting
    public boolean substractChips(int substract){
        if (chipsCount >= substract){
            chipsCount = chipsCount - substract;
            return true;
        }
        else{
            return false;
        }
    }

    //for ChipsHandler to reset amountBet after each round
    public void resetAmountBetThisRound(){
        amountBetThisRound = 0;
    }

    //to get information about playerhand
    public PlayerHand getPlayerhand(){
        return playerhand;
    }
    //to get chipsCount
    public int getChipsCount(){
        return chipsCount;
    }
    //to get amountBetThisRound
    public int getAmountBetThisRound(){
        return amountBetThisRound;
    }

    //method for calling
    public void call(){
        int remainingAmount;
        remainingAmount = chipsHandler.getHighestBidThisRound() - amountBetThisRound;
        if (substractChips(remainingAmount)){
            amountBetThisRound += remainingAmount;
            chipsHandler.addToPot(remainingAmount);
        }
    }

    //method for raising, to call you have to give the argument of the whole raise sum
    public void raise(int amount){
        if (amount > chipsHandler.getHighestBidThisRound()){
            if (substractChips(amount)) {
                amountBetThisRound += amount;
                chipsHandler.addToPot(amount);
            }
        }
    }

    public void check(){
        hasCheckedThisRound = true;
    }

    public void resetHasChecked(){
        hasCheckedThisRound = false;
    }

    public boolean getHasCheckedThisRound(){
        return hasCheckedThisRound;
    }

    //method for folding
    public void fold(){
        chipsHandler.somebodyHasFolded();
        fold = true;
    }

    //chipshandling has to check if player folded
    public boolean checkFolded(){
        return fold;
    }


    //Issue for Niklas to complete Method
    public void checkAction(){

    }


}
