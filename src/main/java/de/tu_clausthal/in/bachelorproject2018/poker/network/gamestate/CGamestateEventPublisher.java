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
        for (String l_table : messagesSend.keySet()){
            if (!EGamestateManagement.INSTANCE.apply(l_table).getActualIndex().equals(messagesSend.get(l_table))){
                IGamestate l_gamestate = EGamestateManagement.INSTANCE.apply(l_table);

                int messagesAll = l_gamestate.getActualIndex().intValue();
                int messagesToSend = messagesAll - messagesSend.get(l_table).intValue();


                for (int i = 0 ; i < messagesToSend ; i++){
                    AtomicInteger l_index = messagesSend.get(l_table);
                    IGamestateMessage l_message = l_gamestate.getSpecificMessage(l_index.incrementAndGet());
                    eventPublisher.publishEvent(new CMessageEvent(this, l_message, ETables.INSTANCE.apply(l_table)));
                }
            }
        }
    }

    @Scheduled(fixedRate = 10000)
    public void getGamestates(){
        for (IGamestate l_gamestate : EGamestateManagement.INSTANCE.getGamestates()){
            if (!messagesSend.containsKey(l_gamestate.getTable().name())){
                messagesSend.put(l_gamestate.getTable().name(), new AtomicInteger());
            }
        }
    }
}

