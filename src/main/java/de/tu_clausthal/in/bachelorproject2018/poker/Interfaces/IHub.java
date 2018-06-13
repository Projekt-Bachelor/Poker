package de.tu_clausthal.in.bachelorproject2018.poker.Interfaces;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.Player;

import java.util.Map;

public interface IHub {

    Map<Number, String> getMap();

    void addPlayer(Player player);

    int getSize();

}
