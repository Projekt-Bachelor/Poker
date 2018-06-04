package de.tu_clausthal.in.bachelorproject2018.poker;

import java.util.ArrayList;

public class CardDealer {
    private Deck playDeck = new Deck();
    private StartHub gameHub;
    private ArrayList<Card> tableCards = new ArrayList<Card>();
    private static final CardDealer cardDealer = new CardDealer();

    private CardDealer(){
        gameHub = gameHub.getInstance();
    }

    public static CardDealer getInstance(){
        return cardDealer;
    }

    //reset deck for each round
    public void resetForNextRound(){
        playDeck.clearDeck();
        playDeck.initDeck();
        playDeck.shuffle();
        tableCards.clear();
    }
    //init deck at the start of game
    public void firstDeckOfTheGame(){
        playDeck.initDeck();
        playDeck.shuffle();
    }
    //look at tablecards
    public ArrayList<Card> getTableCards(){
        return tableCards;
    }
    public Deck getDeck(){
        return playDeck;
    }

    //3 play phases
    public void layFlop(){
        tableCards.add(playDeck.removeTopCard());
        tableCards.add(playDeck.removeTopCard());
        tableCards.add(playDeck.removeTopCard());
    }
    public void layTurn(){
        tableCards.add(playDeck.removeTopCard());
    }
    public void layRiver(){
        tableCards.add(playDeck.removeTopCard());
    }
}
