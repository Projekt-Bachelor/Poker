package de.tu_clausthal.in.bachelorproject2018.poker;

public enum CardValue {
    two(2), three(3), four(4), five(5), six(6),
    seven(7), eight(8), nine(9), ten(10), jack(11),
    queen(12), king(13), ace(14);

    private final int value;

    /**
     * @param value
     */
    //Constructor
    CardValue(int value){
        this.value = value;
    }

    // to read value
    public int getValue(){
        return value;
    }
}
