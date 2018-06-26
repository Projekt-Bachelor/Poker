package de.tu_clausthal.in.bachelorproject2018.poker.game.cards;

public class Card {
    //private value + suit of card
    private CardSuit suit;
    private CardValue value;


    /**
     * Constructor for Cards
     * @param suit
     * @param value
     */
    public Card(CardSuit suit, CardValue value){
        this.suit = suit;
        this.value = value;
    }


    /**
     * Get Suit information of card
     * @return integer of suit of card
     */
    public int getSuitIndex(){
        return suit.getSuitIndex();
    }

    /**
     * Get Suit information of card
     * @return string of suit of card
     */
    public String getStringSuit(){
        return suit.getStringSuit();
    }

    /**
     * Get Value information of card
     * @return integer value of card
     */
    public int getValue(){
        return value.getValue();
    }

    /**
     *
     * @return String of card with all relevant information
     */
    public String toString(){
        return value.getValue() + " of " + suit.getStringSuit();
    }
}
