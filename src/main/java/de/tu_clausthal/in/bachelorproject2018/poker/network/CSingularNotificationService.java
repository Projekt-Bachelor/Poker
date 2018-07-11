package de.tu_clausthal.in.bachelorproject2018.poker.network;


import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CNotifyPlayerEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

@Service
public class CSingularNotificationService implements ApplicationListener<CNotifyPlayerEvent> {


    @Override
    public void onApplicationEvent(CNotifyPlayerEvent notifyPlayerEvent) {

        try {
            notifyPlayerEvent.getPlayer().getSession().sendMessage(new TextMessage(notifyPlayerEvent.getMessage()));
        } catch (IOException e) {
            //TODO - throw Exception
            e.printStackTrace();
        }


    }
}
