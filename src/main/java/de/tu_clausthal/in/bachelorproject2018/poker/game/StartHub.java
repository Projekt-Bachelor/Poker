package de.tu_clausthal.in.bachelorproject2018.poker.game;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.CPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.round.ERound;

import java.util.ArrayList;
import java.util.Arrays;


public class StartHub {
    private final ArrayList<IPlayer> players = new ArrayList<>();
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
     * @todo das kann hier raus
     */
    public IPlayer createPlayer(){
        return new CPlayer( "" );
    }

    /**
     * add a player to the list of players
     * @param newPlayer as Player
     */
    public void addPlayer(CPlayer newPlayer){
        players.add(newPlayer);
    }

    /**
     * getter for the playerlist
     * @return playerlist as ArrayList<Player>
     */
    public ArrayList<IPlayer> getPlayerList(){
        return players;
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
        ChipsHandling chipsHandler = ChipsHandling.getInstance();
        CardDealer cardDealer = CardDealer.getInstance();
        WinEvaluation winEvaluation = WinEvaluation.getInstance();
    }

    /**
     * start the game with initializing the deck and adding the start amount of chips to each players count
     */
    public void startGame(){
        cardDealer.firstDeckOfTheGame();

        /*
         * @todo IPlayer-Klasse muss das addChips bekommen
         */
        //players.forEach( i -> i.addChips( chipsStartAmount ) );
        // for (CPlayer player: players){
        //    player.addChips(chipsStartAmount);
        //}
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
        // @todo das sollte man niemals machen, dazu bitte ein Nullobjekt im IPlayer definieren
        CPlayer winner = null;
        ChipsHandling.getInstance().resetHand();
        cardDealer.resetForNextRound();
        ChipsHandling.getInstance().forceBlinds();

        /**
         * @todo analog wie oben mittels Stream iterieren
         */
        //for(CPlayer player: players){
        //    player.getPlayerhand().takeCard(cardDealer.getDeck().removeTopCard());
        //    player.getPlayerhand().takeCard(cardDealer.getDeck().removeTopCard());
        //}

        Arrays.stream( ERound.values() )
              // hole aus dem enum das IRoundAction Object
              .map( i -> i.get() )
              // führe in dem IRoundAction Objectk get aus
              .map( i -> i.get() )
              // filtere IRoundAction Objekt, ob gestoppt werden muss
              .filter( i -> i.stop() )
              // wenn das erste IRoundAction Objekt stop == true sagt
              .findFirst();

        /*
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
        */
    }

}