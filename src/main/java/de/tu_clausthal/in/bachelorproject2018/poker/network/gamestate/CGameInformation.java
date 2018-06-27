package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

public class CGameInformation {

    //TODO - Add other objects for the UI later.
    private final String text;

    public CGameInformation(String message){
        this.text = message;
    }

    public String getText() {
        return text;
    }
}
