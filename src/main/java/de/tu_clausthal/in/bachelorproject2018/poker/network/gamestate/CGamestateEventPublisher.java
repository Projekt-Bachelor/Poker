package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.IGamestateMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CMessageEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CGamestateEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    private Map<String, AtomicInteger> messagesSend = new ConcurrentHashMap<>();

    public CGamestateEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(fixedRate = 5000)
    public void publishEvent(){
        for (String table : messagesSend.keySet()){
            if (!EGamestateManagement.INSTANCE.apply(table).getActualIndex().equals(messagesSend.get(table))){
                IGamestate l_gamestate = EGamestateManagement.INSTANCE.apply(table);

                int messagesAll = l_gamestate.getActualIndex().intValue();
                int messagesToSend = messagesAll - messagesSend.get(table).intValue();


                for (int i = 0 ; i < messagesToSend ; i++){
                    int l_index = messagesSend.get(table).incrementAndGet();
                    IGamestateMessage l_message = l_gamestate.getSpecificMessage(l_index);
                    eventPublisher.publishEvent(new CMessageEvent(this, l_message, ETables.INSTANCE.apply(table)));
                }
            }
        }
    }

    @Scheduled(fixedRate = 5000)
    public void getGamestates(){
        for (IGamestate l_gamestate : EGamestateManagement.INSTANCE.getGamestates()){
            messagesSend.put(l_gamestate.getTable().name(), new AtomicInteger());
        }
    }
}

