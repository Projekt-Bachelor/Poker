package de.tu_clausthal.in.bachelorproject2018.poker.game.hubs;


import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck.HandStatistic;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.EGamestateManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CGameMessage;
import org.pmw.tinylog.Logger;

import java.util.ArrayList;


public class ChipsHandling {
    private int pot= 0;
    private int smallBlindIndex = 0;
    private int bigBlindIndex = 1;
    private int bigBlindAmount = 100;
    private int smallBlindAmount = 50;
    private int highestBidThisRound = 0;
    private boolean newRound = false;
    private int playersInThisRound;
    private GameHub gameHub;


    public ChipsHandling(GameHub gameHub){
        this.gameHub = gameHub;
    }
    /**
     * add the getAmountBetThisRound to the pot
     * gets called when a player bets anything
     * newRound will be set false, because somebody hast bet something this bettinground
     * if the player is the one with the most bet this round (in the case of raising), update highestBidThisRound
     * @param amount, highestBidOfPlayer
     */
    public void addToPot(int amount, int highestBidOfPlayer){
        pot += amount;
        if (highestBidOfPlayer > highestBidThisRound){
            highestBidThisRound = highestBidOfPlayer;
        }
        newRound = false;
    }

    /**
     * Get the BigBlind for displaying information
     * @return BigBlindAmount as integer
     */
    public int getBigBlindAmount(){
        return bigBlindAmount;
    }

    /**
     * Get the SmallBlind for displaying information
     * @return SmallBlindAmount as integer
     */
    public int getSmallBlindAmount(){
        return (smallBlindAmount);
    }

    /**
     * get the Highest Bid for displaying information
     * @return HighestBigThisRound as integer
     */
    public int getHighestBidThisRound(){
        return highestBidThisRound;
    }

    /**
     * go clockwise through the playerlist, to check who has to pay blinds
     * if any index is at the last index of the playerlist, start again at 0
     */
    public void nextBlind(){
        if (smallBlindIndex >= gameHub.getPlayerList().size()-1){
            smallBlindIndex = 0;
        }
        else {
            smallBlindIndex++;
        }
        if (bigBlindIndex >= gameHub.getPlayerList().size() -1 ){
            bigBlindIndex = 0;
        } else {
            bigBlindIndex++;
        }

        if (bigBlindIndex == smallBlindIndex){
            bigBlindIndex++;
        }
    }

    /**
     * double the getAmountBetThisRound of the blinds
     * do this every couple of rounds to speed up the game
     */
    public void doubleBlinds(){
        smallBlindAmount = bigBlindAmount;
        bigBlindAmount = 2 * bigBlindAmount;
    }

    public int getPot(){
        return pot;
    }

    /**
     * check who starts the round
     * should be the player next to bigBlind, but if bigBlind is highest index of the array, start at 0
     * @return index of round starter
     */
    public int getRoundStarter(){
        if (bigBlindIndex == gameHub.getPlayerList().size() -1){
            return 0;
        } else {
            return bigBlindIndex +1;
        }
    }

    /**
     * force bet the blinds of the player with the relevant indexes
     * update the highestBidThisRound to the bigBlind
     * fügt die Blinds dem Pot hinzu
     */
    public void forceBlinds(){

        gameHub.getPlayerList().get(bigBlindIndex).substractChips(bigBlindAmount);
        Logger.info(gameHub.getPlayerList().get(bigBlindIndex).getName() + " hat folgendes gesetzt " + bigBlindAmount);
        gameHub.getPlayerList().get(bigBlindIndex).addToAmountBetThisRound(bigBlindAmount);

        gameHub.getPlayerList().get(smallBlindIndex).substractChips(smallBlindAmount);
        Logger.info(gameHub.getPlayerList().get(smallBlindIndex).getName() + " hat folgendes gesetzt " + smallBlindAmount);
        gameHub.getPlayerList().get(smallBlindIndex).addToAmountBetThisRound(smallBlindAmount);

        highestBidThisRound = bigBlindAmount;

        pot += bigBlindAmount + smallBlindAmount;
        EGamestateManagement.INSTANCE.apply(gameHub.getTable().name()).addGameMessage(
                new CGameMessage("Neue Runde! Die Blinds wurden gesetzt! Dabei hat " +
                        gameHub.getPlayerList().get(smallBlindIndex).getName() + " " + getSmallBlindAmount() +" Chips als Smallblind gesetzt. "
                        + gameHub.getPlayerList().get(bigBlindIndex).getName() + " hat " + getBigBlindAmount() + " Chips als Bigblind gesetzt." , gameHub.getTable()));
        EGamestateManagement.INSTANCE.apply(gameHub.getTable().name()).addGameMessage(
                new CGameMessage("Der Pot beträgt jetzt " + getPot() , gameHub.getTable()));
    }

    /**
     * update who to Ask, return player next in line
     * @param  player as Player
     * @return nextPlayer as Player
     */
    public IPlayer updateWhoToAsk(IPlayer player){
        //find player in the playerlist
        int index = 0;
        for (int i = 0; i<gameHub.getPlayerList().size(); i++){
            if (player == gameHub.getPlayerList().get(i)){
                index = i;
            }
        }
        //count to next player
        index++;
        //if the player was the last player in the list, start with the first
        if (index >= gameHub.getPlayerList().size()){
            index = 0;
        }
        return gameHub.getPlayerList().get(index);
    }

    /**
     * counting down how many are still in the round
     * used to check, if at least 2 players are playing the round
     */
    public void somebodyHasFolded(){
        playersInThisRound--;
    }




    /**
     * check if there are at least 2 players in the round
     * @return boolean if there at least 2 players
     */
    public boolean continuePlayingRound(){
        if (playersInThisRound>1){
            return true;
        } else {
            return false;
        }
    }



    /**
     * divide pot by winners, and add the amount to the winners
     * @param winners as ArrayList<CPlayer>
     */
    public void distributePotToWinner(ArrayList<HandStatistic> winners)
    {
        int amount = pot/winners.size();
        for (HandStatistic handStatistic : winners){
            handStatistic.getPlayer().addChips(amount);
        }
        pot = 0;
    }


    /**
     * reset after every betting round
     * resets all nessessary variables to the needed default values
     */
    public void resetRound(){
        newRound = true;
        for (IPlayer player: gameHub.getPlayerList()){
            player.resetHasChecked();
        }

    }

    /**
     * getter für newRound
     * @return newRound as boolean
     */
    public boolean getNewRound(){
        return newRound;
    }

    /**
     * reset everything after a fully played hand
     * resets the same variables as resetRound but furthermore resets pot, handcards, folded etc.
     * switches the smallblind and bigblind players
     */
    public void resetHand(){
        nextBlind();
        highestBidThisRound = 0;
        newRound = false;
        pot = 0;
        playersInThisRound = gameHub.getPlayerList().size();

        for (IPlayer player: gameHub.getPlayerList()){
            player.resetAllIn();
            player.resetAmountBetThisRound();
            player.resetHasChecked();
            player.getPlayerhand().resethandCards();
            player.resetFolded();
        }

    }



}
