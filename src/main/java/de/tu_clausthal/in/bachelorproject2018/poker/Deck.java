package de.tu_clausthal.in.bachelorproject2018.poker;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;
    //to shuffle we need randomness
    Random rand = new Random();

    //Constructor
    public Deck(){
        cards = new ArrayList<Card>();
    }

    //create deck

    /**
     *
     */
    public void initDeck(){
        //double loop, run through suit and value to create every single card
        for (CardSuit suit: CardSuit.values()){
            for (CardValue value: CardValue.values()){
                Card card = new Card(suit, value);
                cards.add(card);
            }
        }
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    //clear Deck for new round
    public void clearDeck(){
        cards.clear();
    }
    // simple algorithm to shuffle, maybe switch algorithm to more randomness

    /**
     *
     */
    public void shuffle(){
        //maybe repeat this a couple of times, to shuffle even more
        for (int i = cards.size()-1; i>0; i--){
            //get a random number for pick between 0-i
            int pick = rand.nextInt(i);
            //pick random card
            Card randomCard = cards.get(pick);
            //pick card to switch with randCard
            Card card = cards.get(i);
            //switch positions of cards
            cards.set(i, randomCard);
            cards.set(pick, card);
        }
    }

    //deal the top card of the deck, and remove it from the deck. return card to give it to player/board

    /**
     *
     * @return
     */
    public Card removeTopCard(){
        Card dealtCard;
        dealtCard = cards.get(cards.size()-1);
        cards.remove(cards.size()-1);
        return dealtCard;
    }

    //show remaining content of deck for testing?

    /**
     *
     * @return
     */
    public String showDeck(){
        String output = "";
        for (Card c: cards){
            output += c.toString() + ", ";
        }
        return output;
    }

}
