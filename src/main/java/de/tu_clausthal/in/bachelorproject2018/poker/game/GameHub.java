package de.tu_clausthal.in.bachelorproject2018.poker.game;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.CPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck.DetermineWinner;
import de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck.EWinCheck;
import de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck.HandStatistic;

import java.util.ArrayList;
import java.util.Arrays;


public class GameHub {
    private final ArrayList<IPlayer> players = new ArrayList<>();
    private final CardDealer cardDealer;
    private final ChipsHandling chipsHandler;
    private int chipsStartAmount;
    private final DetermineWinner findWinner;
    private final ITable table;

    public GameHub( ITable Tisch){
        cardDealer = new CardDealer(this);
        chipsHandler = new ChipsHandling(this);
        findWinner = new DetermineWinner();
        this.table = Tisch;
    }

    /**
     * getter for table
     * @return table as ITable
     */
    public ITable getTable(){
        return table;
    }

    /**
     * getter für findwinner
     * @return findwinner as determineWinner
     */
    public DetermineWinner getDetermineWinner(){
        return findWinner;
    }

    /**
     * add a player to the list of players
     * @param newPlayer as CSessionRegistration
     */
    public void addPlayer(CPlayer newPlayer){
        players.add(newPlayer);
    }

    /**
     * getter for the playerlist
     * @return playerlist as ArrayList<CSessionRegistration>
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
     * getter for the chipsHandler
     * @return chipsHandler as ChipsHandling
     */
    public ChipsHandling getChipsHandler(){
        return chipsHandler;
    }

    /**
     * start the game with initializing the deck and adding the start getAmountBetThisRound of chips to each players count
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
     * Durchlaufen einer Winevaluation ohne Vergleiche
     * es wird für jeden Spieler eine Handstatistic erstellt und übergeben
     * die Handstatistic wird für jeden Spieler befüllt und kann am Ende verglichen werden
     * findWinner vergleicht die Handstatistics
     */
    public void handleWinEvaluation(){

        ArrayList<HandStatistic> handStatisticList = new ArrayList<HandStatistic>();
        for (IPlayer player : players){
            HandStatistic handStatistic = new HandStatistic(player);
            handStatisticList.add(handStatistic);
            if (!player.checkfolded()){
                Arrays.stream( EWinCheck.values() )
                        .map (i -> i.apply( table ) )
                        .forEach(i-> i.apply( handStatistic));
            }

        }
        //gibt eine ArrayList von den Gewinnern wieder

        chipsHandler.distributePotToWinner(findWinner.findWinner(handStatisticList));
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
        /*
        chipsHandler.resetHand();
        cardDealer.resetForNextRound();
        chipsHandler.forceBlinds();
        */
        /**
         * @todo analog wie oben mittels Stream iterieren
         */
        //for(CPlayer player: players){
        //    player.getPlayerhand().takeCard(cardDealer.getDeck().removeTopCard());
        //    player.getPlayerhand().takeCard(cardDealer.getDeck().removeTopCard());
        //}
        /**
         * Durchlaufen einer ganzen Runde, übergebe aus dem Tisch die Spielerliste
         *
         * das ist alles im Table drin
        ERound.generate( players )  //.collect( Collectors.toCollection( new Stack ) )
              // führe in dem IRoundAction Objekt get aus
              .map( Supplier::get ) // äquivalent i -> i.get()
              // filtere IRoundAction Objekt, ob gestoppt werden muss
              .filter( IRoundAction::stop )
              // wenn das erste IRoundAction Objekt stop == true sagt
              .findFirst();
        */
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
