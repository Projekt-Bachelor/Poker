package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.BasicTestFiles.Network_BaseResponse;
import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.Player;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.CPlayer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class CPlayerController {

    @MessageMapping("/createPlayer")
    @SendTo("/topic/tables")
    public Network_BaseResponse createPlayer(Player player) throws Exception {
        CPlayer newPlayer = new CPlayer(player.getName());

        //TODO - Implement player Validation
        return new Network_BaseResponse("Player: " + player.getName() + " was created successfully", 100);
    }
}