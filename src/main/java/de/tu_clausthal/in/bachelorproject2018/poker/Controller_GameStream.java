package de.tu_clausthal.in.bachelorproject2018.poker;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.GameInformation;
import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
//@SessionAttributes("GameStateInstance")
public class Controller_GameStream {

    /*@Autowired
    GameStateService gameStateService;

    @RequestMapping(value = "/gamestate", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<String> showGameState(@ModelAttribute("GameStateInstance") GameState gameState){
        return gameStateService.getCurrentGameState(gameState.getList());
    }

    @ModelAttribute("GameStateInstance")
    public GameState refreshGameState(){
        return GameState.getGameStateInstance();
    }*/

    @Autowired
    private GameStateService gameStateService;

    @RequestMapping(value = "/gamestateTEST", method = RequestMethod.GET, produces = "text/event-stream")
    public Flux<ServerSentEvent<GameInformation>> getInfiniteGameState(){
        return gameStateService.getGameInformations();
    }
}