package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages;

public class CGameMessage implements IGamestateMessage{

    private final String text;

    public CGameMessage(String message){
        this.text = message;
    }

    public String getText() {
        return text;
    }
}
