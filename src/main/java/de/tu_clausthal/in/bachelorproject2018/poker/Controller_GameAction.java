package de.tu_clausthal.in.bachelorproject2018.poker;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.Network_GameAction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller_GameAction {

    private static final String template = "You choose: %s !";
    private final AtomicLong counter =  new AtomicLong();

    @RequestMapping("/action")
    public Network_GameAction network_gameAction(@RequestParam(value = "gameAction", defaultValue = "Default") String gameAction) {
        return new Network_GameAction(counter.incrementAndGet(),
                String.format(template, gameAction));
    }
}
