package de.tu_clausthal.in.bachelorproject2018.poker.network.websocket_test;

import com.google.gson.Gson;
import de.tu_clausthal.in.bachelorproject2018.poker.network.Tokens.ETokenLinker;
import de.tu_clausthal.in.bachelorproject2018.poker.network.websocket.ESessionManagement;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
public class CSocketHandler extends TextWebSocketHandler {

    /**
     *
     * In dieser Klasse muss die Registration Nachricht abgefangen werden und die jeweilige WeboscketSession im
     * ESessionManagement angepasst werden (ACHTUNG CSession muss auch angepasst werdem). Registration kann dann erstmal
     * komplett in diesem Handler stattfinden!
     *
     * Im CNotificationService anschließend beim Auftreten des Events eine Nachricht über die WebsocketSession
     * herrausschicken. (Einmal über alle Sessions der Table iterieren und an jeden die selbe Nachricht schicken!
     *
     * Websocket.js in Game.js integrieren!
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {

        Map arguments = new Gson().fromJson(message.getPayload(), Map.class);
        if (arguments.get("message-type").equals("registration")) {
            String l_sessionId = ETokenLinker.INSTANCE.apply(UUID.fromString(arguments.get("token").toString()));

            ESessionManagement.INSTANCE.apply(l_sessionId).setSession(session);
            ESessionManagement.INSTANCE.apply(l_sessionId).getPlayer().setSession(session);
        }
        //session.sendMessage(new TextMessage(new Gson().toJson(new CNotification("test")
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

    }


}
