package de.tu_clausthal.in.bachelorproject2018.poker;

public class Card {
    //private value + suit of card
    private CardSuit suit;
    private CardValue value;

    //Constructor

    /**
     *
     * @param suit
     * @param value
     */
    public Card(CardSuit suit, CardValue value){
        this.suit = suit;
        this.value = value;
    }

    //read data of card

    /**
     *
     * @return
     */
    public int getSuitIndex(){
        return suit.getSuitIndex();
    }
    public String getStringSuit(){
        return suit.getStringSuit();
    }

    /**
     *
     * @return
     */
    public int getValue(){
        return value.getValue();
    }
    //read data to test

    /**
     *
     * @return
     */
    public String toString(){
        String output= value.getValue() + " of " + suit.getStringSuit();
        return output;
    }
}
