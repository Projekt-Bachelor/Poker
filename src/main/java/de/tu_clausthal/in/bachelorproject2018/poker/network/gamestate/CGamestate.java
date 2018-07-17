package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.*;
import org.springframework.lang.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CGamestate implements IGamestate {

    private final ITable m_table;

    private final AtomicInteger m_newestInformation = new AtomicInteger();

    private Map<Number, IGamestateMessage> gamestate = new ConcurrentHashMap<>();

    public CGamestate(@NonNull ITable p_table){
        this.m_table = p_table;
    }

    public ITable getTable(){
        return m_table;
    }

    @Override
    public void addGameMessage(@NonNull CGameMessage p_gameMessage) {
        gamestate.put(m_newestInformation.incrementAndGet(), p_gameMessage);
    }

    @Override
    public void addCardMessage(@NonNull CCardMessage p_cardMessage) {
        gamestate.put(m_newestInformation.incrementAndGet(), p_cardMessage);
    }

    @Override
    public void addChipMessage(@NonNull CChipMessage p_chipMessage) {
        gamestate.put(m_newestInformation.incrementAndGet(), p_chipMessage);
    }

    @Override
    public void addNotifyMessage(CNotifyMessage p_notifyMessage) {
        gamestate.put(m_newestInformation.incrementAndGet(), p_notifyMessage);
    }

    @Override
    public IGamestateMessage getSpecificMessage(@NonNull int index) {
        return gamestate.get(index);
    }

    @NonNull
    @Override
    public Number getActualIndex() {
        return m_newestInformation;
    }
}
