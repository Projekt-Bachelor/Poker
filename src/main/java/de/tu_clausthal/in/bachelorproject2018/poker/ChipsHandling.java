package de.tu_clausthal.in.bachelorproject2018.poker;
import java.util.ArrayList;

public class ChipsHandling {
    private ArrayList<Player> players;
    private int pot= 0;
    private int smallBlindIndex = 0;
    private int bigBlindIndex = 1;
    private int bigBlindAmount = 100;
    private int highestBidThisRound = 0;
    private boolean roundBettingFinished = false;
    private boolean newRound = false;
    private int playersInThisRound;

    //method to add players at the start of the game
    public void addPlayer(Player newPlayer){
        players.add(newPlayer);
    }
    //method for the player to add to pot, newRound to be able to check, that nobody has set this round so far (for checking)
    public void addToPot(int amount){
        pot += amount;
        newRound = false;
    }
    //probably not nessessary; DELETE IF NOT NEEDED
    public int getBigBlindAmount(){
        return bigBlindAmount;
    }
    public int getSmallBlindAmoutn(){
        return (bigBlindAmount/2);
    }

    public int getHighestBidThisRound(){
        return highestBidThisRound;
    }

    //keep changing blinds clockwise
    public void nextBlind(){
        if (smallBlindIndex == players.size()-1){
            smallBlindIndex = 0;
        }
        else {
            smallBlindIndex++;
        }
        if (bigBlindIndex == players.size() -1 ){
            bigBlindIndex = 0;
        } else {
            bigBlindIndex++;
        }
        notifyAllClients();
    }

    //method to help with the start of the betting. needed because bigBlindIndex +1 might go out of bounds
    public int getRoundStarter(){
        if (bigBlindIndex == players.size() -1){
            return 0;
        } else {
            return bigBlindIndex +1;
        }
    }

    //force blinds of players
    public void forceBlinds(){
        players.get(bigBlindIndex).raise(bigBlindAmount);
        players.get(smallBlindIndex).raise(bigBlindAmount/2);
        highestBidThisRound = bigBlindAmount;
        notifyAllClients();
    }

    //update whoToAsk after each action, go clockwise
    public int updateWhoToAsk(int previosWhoToAsk){
        if (previosWhoToAsk == players.size()-1){
            return 0;
        } else {
            return previosWhoToAsk + 1;
        }
    }

    //to update how many players have folded already
    public void somebodyHasFolded(){
        playersInThisRound--;
    }

    /* go through a whole round of betting, ask each player if he has folded.
     if not, and he has to act, ask for action */
    public void checkForBets(){
        int whoToAsk = getRoundStarter();
        /*
         * while loop to check for each player, until everybody has either folded or payed the highestBid
         * newRound is set to false, when the first players adds anything to the pot
         * newRound is there to enable checking, but only if nobody has bet anything this round
         */
        while(!roundBettingFinished){
                if (players.get(whoToAsk).getAmountBetThisRound() < highestBidThisRound || newRound) {
                    if (newRound && players.get(whoToAsk).getHasCheckedThisRound()) {
                        //only happens when everybody checks
                        roundBettingFinished = true;
                    } else {
                        //asks the right player for action
                        if (!players.get(whoToAsk).checkFolded()) {
                            players.get(whoToAsk).checkAction();
                        }
                    }
                } else {
                    //everybody has called the highest bet or folded
                    roundBettingFinished = true;
                }
            whoToAsk = updateWhoToAsk(whoToAsk);
        }
    }


    //reset after every finished round of betting
    public void resetRound(){
        highestBidThisRound = 0;
        roundBettingFinished = false;
        newRound = true;
        for (Player player: players){
            player.resetAmountBetThisRound();
            player.resetHasChecked();
        }
    }
    //reset everything after each fully played hand
    public void resetHand(){
        highestBidThisRound = 0;
        roundBettingFinished = false;
        newRound = false;
        pot = 0;
        playersInThisRound = players.size();
        for (Player player: players){
            player.resetAmountBetThisRound();
            player.resetHasChecked();
        }
    }










    public void notifyAllClients(){
        //completed by Niklas
    }


}
