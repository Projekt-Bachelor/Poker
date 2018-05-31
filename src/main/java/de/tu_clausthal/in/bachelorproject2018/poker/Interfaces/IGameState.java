package de.tu_clausthal.in.bachelorproject2018.poker.Interfaces;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.GameInformation;

import java.util.List;
import java.util.Map;

public interface IGameState {

    List<String> getList();

    void addGameInformation(GameInformation gameInformation);
}
