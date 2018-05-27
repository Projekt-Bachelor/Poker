package de.tu_clausthal.in.bachelorproject2018.poker;

public enum CardSuit {
    club(0),
    spade(1),
    heart(2),
    diamond(3);

    //private final String suitText;
    private final int suitIndex;

    //Constructor
    /*private CardSuit(String suitText){
        this.suitText = suitText;
    }*/
    private CardSuit (int suitIndex){
        this.suitIndex = suitIndex;
    }
    public int getSuitIndex(){
        return suitIndex;

    }

    //to read suit
    public String getStringSuit(){
        switch (suitIndex){
            case 0:
                return "clubs";
            case 1:
                return "spades";
            case 2:
                return "hearts";
            case 3:
                return "diamonds";
            default:
                return "something went wrong";
        }
    }
}
