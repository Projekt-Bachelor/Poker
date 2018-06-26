package de.tu_clausthal.in.bachelorproject2018.poker.network;

public class GameInformation {

    //TODO - Add other objects for the UI later.
    private String text;

    public GameInformation(String message){
        this.text = message;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
