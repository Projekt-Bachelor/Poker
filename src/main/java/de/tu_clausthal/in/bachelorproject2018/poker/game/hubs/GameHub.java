package de.tu_clausthal.in.bachelorproject2018.poker.game.hubs;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.CPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck.DetermineWinner;
import de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck.EWinCheck;
import de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck.HandStatistic;
import de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck.WinnerHand;
import org.pmw.tinylog.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class GameHub {
    private List<IPlayer> players = new ArrayList<>();
    private final CardDealer cardDealer;
    private final ChipsHandling chipsHandler;
    private int chipsStartAmount = 1000;
    private final DetermineWinner findWinner;
    private final ITable table;
    private final WinnerHand winnerHand;

    public GameHub( ITable p_table){
        cardDealer = new CardDealer(this);
        chipsHandler = new ChipsHandling(this);
        findWinner = new DetermineWinner();
        winnerHand = new WinnerHand();
        this.table = p_table;
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
     * getter für winnerhand
     * @return winnerhand as Winnerhand
     */
    public WinnerHand getWinnerHand(){
        return winnerHand;
    }



    /**
     * getter for the playerlist
     * @return playerlist as ArrayList<CSessionRegistration>
     */
    public List<IPlayer> getPlayerList(){
        updatePlayerlist();
        return players;
    }

    /**
     * zieht sich die aktuelle playerList vom Table
     */
    public void updatePlayerlist(){
        players = table.list().stream().collect(Collectors.toList());
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

        for (IPlayer player: getPlayerList()){
            player.addChips(chipsStartAmount);
            Logger.info("Spieler: " + player.getName() + " hat folgende Chips erhalten: " + chipsStartAmount);
        }
    }


}
