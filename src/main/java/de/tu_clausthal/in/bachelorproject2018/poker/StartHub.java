package de.tu_clausthal.in.bachelorproject2018.poker;

import java.util.ArrayList;

public class StartHub {
    private ArrayList<Player> players;
    private ChipsHandling chipsHandler;
    private CardDealer cardDealer;
    private WinEvaluation winEvaluation;
    private int chipsStartAmount;

    private static final StartHub gameHub = new StartHub();

    /**
     * constructor
     */
    private StartHub(){
    }

    /**
     * getInstance
     * @return Instance of the Starthub
     */
    public static StartHub getInstance(){
        return gameHub;
    }

    /**
     * create a new player object, return the player
     * @return a new player as Player
     */
    public Player createPlayer(){
        Player newPlayer = new Player();
        return newPlayer;
    }

    /**
     * add a player to the list of players
     * @param newPlayer as Player
     */
    public void addPlayer(Player newPlayer){
        players.add(newPlayer);
    }

    /**
     * getter for the playerlist
     * @return playerlist as ArrayList<Player>
     */
    public ArrayList<Player> getPlayerList(){
        return players;
    }

    /**
     * getter for the chipshandler
     * @return chipshandler as ChipsHandling
     */
    public ChipsHandling getChipsHandler() {
        return chipsHandler;
    }

    /**
     * getter for the carddealer
     * @return carddealer as CardDealer
     */
    public CardDealer getCardDealer(){
        return cardDealer;
    }

    /**
     * start the programm, save the other important classes in the starthub
     */
    public void startProgram(){
        players = new ArrayList<Player>();
        ChipsHandling chipsHandler = ChipsHandling.getInstance();
        CardDealer cardDealer = CardDealer.getInstance();
        WinEvaluation winEvaluation = WinEvaluation.getInstance();
    }

    /**
     * start the game with initializing the deck and adding the start amount of chips to each players count
     */
    public void startGame(){
        cardDealer.firstDeckOfTheGame();
        for (Player player: players){
            player.addChips(chipsStartAmount);
        }
    }

    /**
     * play a hand of poker, this includes:
     * reseting the winner, the hands of the players and the deck
     * force bet the small blind and big blind
     * deal 2 cards to each player
     * alternating between checking for bets and dealing the cards for the tablecards,
     * reset all nessessary attributes after each betting round, stop if only 1 player is left
     * if more than 1 player is in the final round, check who won and distribute pot to winner
     */
    public void playRound(){
        Player winner = null;
        chipsHandler.resetHand();
        cardDealer.resetForNextRound();
        chipsHandler.forceBlinds();
        for(Player player: players){
            player.getPlayerhand().takeCard(cardDealer.getDeck().removeTopCard());
            player.getPlayerhand().takeCard(cardDealer.getDeck().removeTopCard());
        }
        chipsHandler.checkForBets();
        if(chipsHandler.continuePlayingRound()){
            chipsHandler.resetRound();
            cardDealer.layFlop();
            chipsHandler.checkForBets();
            if(chipsHandler.continuePlayingRound()){
                chipsHandler.resetRound();
                cardDealer.layTurn();
                chipsHandler.checkForBets();
                if(chipsHandler.continuePlayingRound()){
                    chipsHandler.resetRound();
                    cardDealer.layRiver();
                    chipsHandler.checkForBets();
                    if(chipsHandler.continuePlayingRound()){
                        winEvaluation.evaluateHands();
                        winner = winEvaluation.evaluateWinner();
                    }
                } else {
                    winner = chipsHandler.declareWinnerByFolding();
                }
            } else {
                winner = chipsHandler.declareWinnerByFolding();
            }
        } else {
            winner = chipsHandler.declareWinnerByFolding();
        }
        chipsHandler.distributePotToWinner(winner);
    }

}
