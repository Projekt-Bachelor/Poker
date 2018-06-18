package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.Player;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.CPlayer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class CPlayerController {

    @MessageMapping("/createPlayer")
    @SendTo("/topic/tables")
    public void createPlayer(Player player) throws Exception {
        CPlayer newPlayer = new CPlayer(player.getName());
    }
}