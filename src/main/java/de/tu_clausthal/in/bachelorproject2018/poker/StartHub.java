package de.tu_clausthal.in.bachelorproject2018.poker;

import java.util.ArrayList;

public class StartHub {
    private ArrayList<Player> players;
    private ChipsHandling chipsHandler;
    private CardDealer cardDealer;
    private WinEvaluation winEvaluation;

    //Constructor
    public StartHub(){
    }

    //create new Player
    public Player createPlayer(){
        Player newPlayer = new Player(chipsHandler);
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

    public static void main(final String[] p_args ){

    }
    public void startGame(){
        StartHub gameHub = new StartHub();
        players = new ArrayList<Player>();
        ChipsHandling chipsHandler = new ChipsHandling(gameHub);
        CardDealer cardDealer = new CardDealer(gameHub);
        WinEvaluation winEvaluation = new WinEvaluation(gameHub);
        addPlayer(createPlayer());
        addPlayer(createPlayer());
        addPlayer(createPlayer());
        cardDealer.firstDeckOfTheGame();


    }

    public void playRound(){
        Player winner = null;
        chipsHandler.forceBlinds();
        for(Player player: players){
            player.getPlayerhand().takeCard(cardDealer.getDeck().removeTopCard());
        }
        chipsHandler.checkForBets();
        if(chipsHandler.continuePlayingRound()){
            cardDealer.layFlop();
            chipsHandler.checkForBets();
            if(chipsHandler.continuePlayingRound()){
                cardDealer.layTurn();
                chipsHandler.checkForBets();
                if(chipsHandler.continuePlayingRound()){
                    cardDealer.layRiver();
                    chipsHandler.checkForBets();
                    if(chipsHandler.continuePlayingRound()){
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
