package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CGamestate implements IGamestate{

    private final ITable m_table;

    private final AtomicInteger m_newestInformation = new AtomicInteger();

    private Map<Number, CGameInformation> gamestate = new ConcurrentHashMap<>();

    public CGamestate(@Nonnull ITable p_table){
        this.m_table = p_table;
    }

    public ITable getTable(){
        return m_table;
    }

    @Override
    public void addGameinformation(@Nonnull CGameInformation gameInformation) {
        gamestate.put(m_newestInformation.incrementAndGet(), gameInformation);
    }

    @Override
    public CGameInformation getSpecificInformation(@Nonnull int index) {
        return gamestate.get(index);
    }

    @Nonnull
    @Override
    public Number getActualIndex() {
        return m_newestInformation;
    }

}
