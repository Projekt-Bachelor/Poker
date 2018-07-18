package de.tu_clausthal.in.bachelorproject2018.poker.game.cards;

public class Card {
    //private value + suit of card
    private CardSuit m_suit;
    private CardValue m_value;


    /**
     * Constructor for Cards
     * @param p_suit
     * @param p_value
     */
    public Card(CardSuit p_suit, CardValue p_value){
        this.m_suit = p_suit;
        this.m_value = p_value;
    }


    /**
     * Get Suit information of card
     * @return integer of suit of card
     */
    public int getSuitIndex(){
        return m_suit.getSuitIndex();
    }

    /**
     * Get Suit information of card
     * @return string of suit of card
     */
    public String getStringSuit(){
        return m_suit.getStringSuit();
    }

    /**
     * Get Value information of card
     * @return integer value of card
     */
    public int getValue(){
        return m_value.getValue();
    }

    /**
     *
     * @return String of card with all relevant information
     */
    public String toString(){
        return m_value.getValue() + " of " + m_suit.getStringSuit();
    }
}
