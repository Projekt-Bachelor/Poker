package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import de.tu_clausthal.in.bachelorproject2018.poker.game.action.CRaise;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CGameActionController {

    @MessageMapping("/gameAction/raise")
    @SendTo("/topic/game")
    public void raiseAction(CRaise raise) throws Exception {
        //raise.accept();
        System.out.println("Spieler x hat geraiset!");
        //TODO - GameInformation erstellen
    }
}
