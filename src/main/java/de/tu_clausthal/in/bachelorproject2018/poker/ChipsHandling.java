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
    /**
     * Singleton
     */
    private static final ChipsHandling chipsHandler = new ChipsHandling();

    /**
     * Constructor
     */
    private ChipsHandling(){
    gameHub = StartHub.getInstance();
    }

    /**
     * get Instance of Singleton
     * @return Instance of Singleton
     */
    public static ChipsHandling getInstance(){
        return chipsHandler;
    }

    /**
     * add the amount to the pot
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

    /**
     * double the amount of the blinds
     * do this every couple of rounds to speed up the game
     */
    public void doubleBlinds(){
        smallBlindAmount = bigBlindAmount;
        bigBlindAmount = 2 * bigBlindAmount;
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
     */
    public void forceBlinds(){
        gameHub.getPlayerList().get(bigBlindIndex).raise(bigBlindAmount);
        gameHub.getPlayerList().get(smallBlindIndex).raise(smallBlindAmount);
        highestBidThisRound = bigBlindAmount;
    }

    /**
     * update who to Ask, return player next in line
     * @param previosWhoToAsk as integer
     * @return updatedWhoToAsk as integer
     */
    public int updateWhoToAsk(int previosWhoToAsk){
        if (previosWhoToAsk == gameHub.getPlayerList().size()-1){
            return 0;
        } else {
            return previosWhoToAsk + 1;
        }
    }

    /**
     * counting down how many are still in the round
     * used to check, if at least 2 players are playing the round
     */
    public void somebodyHasFolded(){
        playersInThisRound--;
    }

    /**
     * go through a whole round of betting
     * asks each player (if they have not folded alrady) what action they want to take
     * continues until everybody has folded or bet the same amount
     */
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
     * if everybody but one has folded, return the player who didnt fold
     * @return winner as Player
     */
    public Player declareWinnerByFolding(){
        int winnerIndex = 0;
        for (int i= 0; i < gameHub.getPlayerList().size(); i++){
            if (!gameHub.getPlayerList().get(i).checkFolded()){
                winnerIndex = i;
            }
        }
        return gameHub.getPlayerList().get(winnerIndex);
    }

    /**
     * Add the chipsamount in the pot to the players chips
     * @param winner
     */
    public void distributePotToWinner(Player winner){
        winner.addChips(pot);
    }


    /**
     * reset after every betting round
     * resets all nessessary variables to the needed default values
     */
    public void resetRound(){
        highestBidThisRound = 0;
        roundBettingFinished = false;
        newRound = true;
        for (Player player: gameHub.getPlayerList()){
            player.resetAmountBetThisRound();
            player.resetHasChecked();
        }
    }

    /**
     * reset everything after a fully played hand
     * resets the same variables as resetRound but furthermore resets pot, handcards, folded etc.
     */
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
