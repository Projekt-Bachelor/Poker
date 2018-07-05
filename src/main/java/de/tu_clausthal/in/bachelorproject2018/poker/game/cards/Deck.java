package de.tu_clausthal.in.bachelorproject2018.poker.game.cards;

import java.util.*;
import java.util.stream.Collectors;

public class Deck implements Iterator<Card>{
    private Stack<Card> cards;



    /**
     * constructor
     */
    public Deck(){

        List<Card> cardList = Arrays.stream(CardSuit.values()).flatMap(i -> Arrays.stream(CardValue.values())
                . map(j -> new Card(i, j))).collect(Collectors.toList());
        Collections.shuffle(cardList);
        cards.addAll(cardList);
    }


    /**
     * initialize deck, create one card for each value and suit
     * add each card to the deck, the deck is sorted at this moment
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

    /**
     * getter
     * @return cards in the deck as ArrayList<Card>
     */
    public List<Card> getCards(){
        return cards;
    }

    /**
     * clear the cards, deletes all cards in the deck
     */
    public void clearDeck(){
        cards.clear();
    }




    //deal the top card of the deck, and remove it from the deck. return card to give it to player/board

    /**
     * remove the topcard of the deck, return the card and delete the element of the deck
     * @return topcard of the deck as Card
     */
    public Card removeTopCard(){
        Card dealtCard;
        dealtCard = cards.get(cards.size()-1);
        cards.remove(cards.size()-1);
        return dealtCard;
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
