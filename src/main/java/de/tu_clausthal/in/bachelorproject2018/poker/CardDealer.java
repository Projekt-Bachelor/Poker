package de.tu_clausthal.in.bachelorproject2018.poker;

import java.util.ArrayList;

public class CardDealer {
    private Deck playDeck = new Deck();
    private StartHub gameHub;
    private ArrayList<Card> tableCards = new ArrayList<Card>();
    private static final CardDealer cardDealer = new CardDealer();

    /**
     * Constructor
     */
    private CardDealer(){
        gameHub = gameHub.getInstance();
    }

    /**
     * get Instance of Singleton
     * @return Instance of cardDealer
     */
    public static CardDealer getInstance(){
        return cardDealer;
    }

    /**
     * reset Deck for next Hand to be played
     * refill the deck with nwe cards and shuffle
     * clear the tablecards, to be able to lay a new Flop/Turn/River
     */
    public void resetForNextRound(){
        playDeck.clearDeck();
        playDeck.initDeck();
        playDeck.shuffle();
        tableCards.clear();
    }

    /**
     * get the deck ready at the start of the game
     * like resetForNextRound, except no clearing of tablecards or deck, because no deck/tablecards existed before
     */
    public void firstDeckOfTheGame(){
        playDeck.initDeck();
        playDeck.shuffle();
    }

    /**
     * get the Tablecards to display them or use them for WinEvaluation
     * @return Tablecards as ArrayList<Card>
     */
    public ArrayList<Card> getTableCards(){
        return tableCards;
    }

    /**
     * get the playDeck
     * @return Deck as Deck
     */
    public Deck getDeck(){
        return playDeck;
    }

    /**
     * take the 3 topcards of the deck and add them to the tablecards
     * Flop/Turn/River can be dealt with in a single method, but for clarification purposes are different methods
     */
    public void layFlop(){
        tableCards.add(playDeck.removeTopCard());
        tableCards.add(playDeck.removeTopCard());
        tableCards.add(playDeck.removeTopCard());
    }

    /**
     * take the topcard of the deck and add it to the tablecard
     */
    public void layTurn(){
        tableCards.add(playDeck.removeTopCard());
    }

    /**
     * take the topcard of the deck and add it to the tablecard
     */
    public void layRiver(){
        tableCards.add(playDeck.removeTopCard());
    }
}
