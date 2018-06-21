package de.tu_clausthal.in.bachelorproject2018.poker;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.GameInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Flux;

@Controller
/**
 * This class is responsible for sending a Flux which contain GameInformation to the browser. The advantage of the Flux
 * is that no player needs to refresh the page because the ad adapts dynamically.
 */
public class Controller_GameStream {

    @Autowired
    private GameStateService gameStateService;

    @RequestMapping(value = "/gamestateTEST", method = RequestMethod.GET, produces = "text/event-stream")
    public Flux<ServerSentEvent<GameInformation>> getInfiniteGameState(){
        return gameStateService.getGameInformations();
    }
}