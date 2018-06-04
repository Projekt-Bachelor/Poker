package de.tu_clausthal.in.bachelorproject2018.poker;
import java.util.ArrayList;

public class ChipsHandling {
    private int pot= 0;
    private int smallBlindIndex = 0;
    private int bigBlindIndex = 1;
    private int bigBlindAmount = 100;
    private int smallBlindAmount = 50;
    private int highestBidThisRound = 0;
    private boolean roundBettingFinished = false;
    private boolean newRound = false;
    private int playersInThisRound;
    private StartHub gameHub;
    //singleton
    private static final ChipsHandling chipsHandler = new ChipsHandling();

    private ChipsHandling(){
    gameHub = StartHub.getInstance();
    }

    public static ChipsHandling getInstance(){
        return chipsHandler;
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
    public int getSmallBlindAmount(){
        return (smallBlindAmount);
    }

    public int getHighestBidThisRound(){
        return highestBidThisRound;
    }

    //keep changing blinds clockwise
    public void nextBlind(){
        bigBlindAmount = 2* bigBlindAmount;
        smallBlindAmount = bigBlindAmount/2;
        if (smallBlindIndex == gameHub.getPlayerList().size()-1){
            smallBlindIndex = 0;
        }
        else {
            smallBlindIndex++;
        }
        if (bigBlindIndex == gameHub.getPlayerList().size() -1 ){
            bigBlindIndex = 0;
        } else {
            bigBlindIndex++;
        }
    }

    //method to help with the start of the betting. needed because bigBlindIndex +1 might go out of bounds
    public int getRoundStarter(){
        if (bigBlindIndex == gameHub.getPlayerList().size() -1){
            return 0;
        } else {
            return bigBlindIndex +1;
        }
    }

    //force blinds of players
    public void forceBlinds(){
        gameHub.getPlayerList().get(bigBlindIndex).raise(bigBlindAmount);
        gameHub.getPlayerList().get(smallBlindIndex).raise(bigBlindAmount/2);
        highestBidThisRound = bigBlindAmount;
        notifyAllClients();
    }

    //update whoToAsk after each action, go clockwise
    public int updateWhoToAsk(int previosWhoToAsk){
        if (previosWhoToAsk == gameHub.getPlayerList().size()-1){
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
                if (gameHub.getPlayerList().get(whoToAsk).getAmountBetThisRound() < highestBidThisRound || newRound) {
                    if (newRound && gameHub.getPlayerList().get(whoToAsk).getHasCheckedThisRound()) {
                        //only happens when everybody checks
                        roundBettingFinished = true;
                    } else {
                        //asks the right player for action
                        if (!gameHub.getPlayerList().get(whoToAsk).checkFolded()) {
                            gameHub.getPlayerList().get(whoToAsk).checkAction();
                        }
                    }
                } else {
                    //everybody has called the highest bet or folded
                    roundBettingFinished = true;
                }
            whoToAsk = updateWhoToAsk(whoToAsk);
        }
    }
    public boolean continuePlayingRound(){
        if (playersInThisRound>1){
            return true;
        } else {
            return false;
        }
    }

    public Player declareWinnerByFolding(){
        int winnerIndex = 0;
        for (int i= 0; i < gameHub.getPlayerList().size(); i++){
            if (!gameHub.getPlayerList().get(i).checkFolded()){
                winnerIndex = i;
            }
        }
        return gameHub.getPlayerList().get(winnerIndex);
    }

    public void distributePotToWinner(Player winner){
        winner.addChips(pot);
    }


    //reset after every finished round of betting
    public void resetRound(){
        highestBidThisRound = 0;
        roundBettingFinished = false;
        newRound = true;
        for (Player player: gameHub.getPlayerList()){
            player.resetAmountBetThisRound();
            player.resetHasChecked();
        }
    }
    //reset everything after each fully played hand
    public void resetHand(){
        nextBlind();
        highestBidThisRound = 0;
        roundBettingFinished = false;
        newRound = false;
        pot = 0;
        playersInThisRound = gameHub.getPlayerList().size();
        for (Player player: gameHub.getPlayerList()){
            player.resetAmountBetThisRound();
            player.resetHasChecked();
            player.getPlayerhand().resetHandEvaluation();
            player.getPlayerhand().resethandCards();
            player.resetFolded();
        }
    }










    public void notifyAllClients(){
        //completed by Niklas
    }


}
