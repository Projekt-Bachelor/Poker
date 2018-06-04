package de.tu_clausthal.in.bachelorproject2018.poker;

import java.util.ArrayList;

public class StartHub {
    private ArrayList<Player> players;
    private ChipsHandling chipsHandler;
    private CardDealer cardDealer;
    private WinEvaluation winEvaluation;

    private static final StartHub gameHub = new StartHub();

    //Constructor
    private StartHub(){
    }

    public static StartHub getInstance(){
        return gameHub;
    }

    //create new Player
    public Player createPlayer(){
        Player newPlayer = new Player();
        return newPlayer;
    }
    //method to add players at the start of the game
    public void addPlayer(Player newPlayer){
        players.add(newPlayer);
    }

    public ArrayList<Player> getPlayerList(){
        return players;
    }

    public ChipsHandling getChipsHandler() {
        return chipsHandler;
    }
    public CardDealer getCardDealer(){
        return cardDealer;
    }


    public void startGame(){
        StartHub gameHub = new StartHub();
        players = new ArrayList<Player>();
        ChipsHandling chipsHandler = ChipsHandling.getInstance();
        CardDealer cardDealer = CardDealer.getInstance();
        WinEvaluation winEvaluation = WinEvaluation.getInstance();
        addPlayer(createPlayer());
        addPlayer(createPlayer());
        addPlayer(createPlayer());
        cardDealer.firstDeckOfTheGame();


    }

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
