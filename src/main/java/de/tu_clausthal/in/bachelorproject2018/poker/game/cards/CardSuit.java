package de.tu_clausthal.in.bachelorproject2018.poker.game.cards;

/**CardSuit Class, to be able to identify the 4 different suits of cards
 *
 */
public enum CardSuit {
    club(0),
    spade(1),
    heart(2),
    diamond(3);

    /**
     * Suit als Int
     */
    private final int suitIndex;

    /**
     * Constructor
     * @param suitIndex
     */
    CardSuit (int suitIndex){
        this.suitIndex = suitIndex;
    }

    /**
     * Getter
     * @return Index of suit as integer
     */
    public int getSuitIndex(){
        return suitIndex;

    }

    /**
     * To be able to read the suits of the cards
     * @return Suit of the card as String
     */
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
