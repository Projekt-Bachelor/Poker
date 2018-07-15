package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CCardMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CChipMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CGameMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.IGamestateMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CMessageEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CGamestate implements IGamestate, ApplicationEventPublisherAware {

    private final ITable m_table;

    private final AtomicInteger m_newestInformation = new AtomicInteger();

    private Map<Number, IGamestateMessage> gamestate = new ConcurrentHashMap<>();

    private ApplicationEventPublisher eventPublisher;

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
        this.eventPublisher.publishEvent(new CMessageEvent(this, p_cardMessage, m_table));
    }

    @Override
    public void addChipMessage(@NonNull CChipMessage p_chipMessage) {
        gamestate.put(m_newestInformation.incrementAndGet(), p_chipMessage);
        this.eventPublisher.publishEvent(new CMessageEvent(this, p_chipMessage, m_table));
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

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
