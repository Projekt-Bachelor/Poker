package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import de.tu_clausthal.in.bachelorproject2018.poker.game.action.CRaise;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CGameActionController {

    @MessageMapping("/gameAction/raise")
    @SendTo("/topic/game")
    public void raiseAction(final CRaise raise) throws Exception {

        // http://www.sergialmar.com/2014/03/detect-websocket-connects-and-disconnects-in-spring-4/

        /*
          bei Server-Session Disconnect, Session holen (Map<Session, Map.Entry<String, String>> - Map mit Sessions und Table und Spieler)
          und über ETables.apply( tablename ).kick( player ) dann Spieler entfernen
         */

        //raise.accept();
        System.out.println("Spieler x hat geraiset!");
        //TODO - GameInformation erstellen
    }
}
