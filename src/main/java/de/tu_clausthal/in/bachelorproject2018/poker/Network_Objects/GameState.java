package de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects;

import de.tu_clausthal.in.bachelorproject2018.poker.Interfaces.IGameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class GameState implements IGameState {

    private List<String> gameInformationList = new ArrayList<>();

    private static final GameState gameStateInstance = new GameState();

    private GameState(){

    }

    public static GameState getGameStateInstance(){
        return gameStateInstance;
    }


    @Override
    public List<String> getList() {
        return gameInformationList;
    }

    @Override
    public void addGameInformation(GameInformation gameInformation) {
        gameInformationList.add(gameInformation.getText());
    }
}
