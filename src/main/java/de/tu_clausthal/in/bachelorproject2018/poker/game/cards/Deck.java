package de.tu_clausthal.in.bachelorproject2018.poker.game.cards;

import java.util.*;
import java.util.stream.Collectors;

public class Deck implements Iterator<Card>{
    private Stack<Card> cards = new Stack<>();

    /**
     * constructor
     */
    public Deck(){

        List<Card> cardList = Arrays.stream(CardSuit.values()).flatMap(i -> Arrays.stream(CardValue.values())
                .map(j -> new Card(i, j))).collect(Collectors.toList());

        Collections.shuffle(cardList);
        cards.addAll(cardList);
    }

    /**
     * getter
     * @return cards in the deck as ArrayList<Card>
     */
    public List<Card> getCards(){
        return cards;
    }

    //deal the top card of the deck, and remove it from the deck. return card to give it to player/board

    /**
     * remove the topcard of the deck, return the card and delete the element of the deck
     * @return topcard of the deck as Card
     */
    public Card removeTopCard(){
        return cards.pop();
    }



    /**
     * show the remaining cards in the deck for testing purposes
     * @return all remaining cards as a long string
     */
    public String showDeck(){
            String output = "";
            for (Card c: cards){
                output += c.toString() + ", ";
            }
            return output;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Card next() {
        return cards.pop();
    }
}
