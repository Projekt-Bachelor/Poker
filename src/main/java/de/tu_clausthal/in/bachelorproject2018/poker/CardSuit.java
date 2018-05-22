package de.tu_clausthal.in.bachelorproject2018.poker;

public enum CardSuit {
    club("club"),
    spade("spade"),
    heart("heart"),
    diamond("diamond");

    private final String suitText;

    /**
     * @param suitText
     */
    //Constructor
    private CardSuit(String suitText){
        this.suitText = suitText;
    }

    //to read suit
    public String getSuit(){
        return suitText;
    }
}
