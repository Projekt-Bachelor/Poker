package de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects;

import de.tu_clausthal.in.bachelorproject2018.poker.Interfaces.IHub;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class contains a list of all players currently in the game.
 */
public class Hub implements IHub {

    private Map<Number, String> playerMap = new HashMap<Number, String>();
    private final AtomicLong counter = new AtomicLong();

    private static final Hub hubInstance = new Hub();

    /**
     * Default Construktor
     */
    private Hub(){

    }

    /**
     * Method related to Singleton Pattern
     * @return hubInstance of Hub
     */
    public static Hub getHubInstance(){
        return hubInstance;
    }

    @Override
    public Map<Number, String> getMap() {
        return playerMap;
    }

    @Override
    public void addPlayer(Player player) {
        playerMap.put(counter.getAndIncrement(), player.getName());
    }

    @Override
    public int getSize() {
        return playerMap.size();
    }
}
