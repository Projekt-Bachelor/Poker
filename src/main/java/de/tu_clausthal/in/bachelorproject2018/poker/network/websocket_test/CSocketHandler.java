package de.tu_clausthal.in.bachelorproject2018.poker.network.websocket_test;

import com.google.gson.Gson;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.Tokens.ETokens;
import de.tu_clausthal.in.bachelorproject2018.poker.network.websocket.CSession;
import de.tu_clausthal.in.bachelorproject2018.poker.network.websocket.ESessionManagement;
import org.javatuples.Triplet;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.sql.Timestamp;
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
            final Triplet<String, String, Timestamp> l_security = ETokens.INSTANCE.apply((UUID) arguments.get("token"));

            IPlayer l_player = ETables.INSTANCE.apply(l_security.getValue0()).list()
                    .stream().filter(i -> i.getName().equalsIgnoreCase(l_security.getValue1())).findFirst().get();

            l_player.setSession(session);

            ITable l_table = ETables.INSTANCE.apply(l_security.getValue0());

            ESessionManagement.INSTANCE.add(new CSession(session, l_table, l_player));
        }
        //session.sendMessage(new TextMessage(new Gson().toJson(new CNotification("test")
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

    }


}
