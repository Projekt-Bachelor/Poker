package de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects;

import de.tu_clausthal.in.bachelorproject2018.poker.Interfaces.IGameState;

import java.util.ArrayList;
import java.util.List;

/**
 * This singleton holds the complete GameState (list of GameInformations).
 */
public class GameState implements IGameState {

    private List<GameInformation> gameInformationList = new ArrayList<>();

    private static final GameState gameStateInstance = new GameState();

    private GameState(){

    }

    public static GameState getGameStateInstance(){
        return gameStateInstance;
    }


    @Override
    public List<GameInformation> getList() {
        return gameInformationList;
    }

    @Override
    public void addGameInformation(GameInformation gameInformation) {
        gameInformationList.add(gameInformation);
    }

    @Override
    public int getSize(){
        return gameInformationList.size();
    }

    @Override
    public GameInformation getElement(int index) {
        return gameInformationList.get(index);
    }
}
