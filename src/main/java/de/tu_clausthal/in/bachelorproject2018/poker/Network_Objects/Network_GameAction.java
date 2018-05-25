package de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects;

public class Network_GameAction {

    private final long id;
    private final String GameAction;

    public Network_GameAction(long id, String gameAction){
        this.id = id;
        this.GameAction = gameAction;
    }

    public long getId() {
        return id;
    }

    public String getGameAction() {
        return GameAction;
    }
}
