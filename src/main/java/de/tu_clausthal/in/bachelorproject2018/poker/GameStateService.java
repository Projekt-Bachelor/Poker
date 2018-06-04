package de.tu_clausthal.in.bachelorproject2018.poker;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.GameInformation;
import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.GameState;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;


@Component
public class GameStateService {

    private final EmitterProcessor<ServerSentEvent<GameInformation>> emitter;
    private int currentListElement = -1;

    /**
     * Constructor which creates the "Server Send" Emitter
     */
    public GameStateService(){
        emitter = EmitterProcessor.create();
    }

    public Flux<ServerSentEvent<GameInformation>> getGameInformations(){
        return emitter.log();
    }

    /**
     *This function checks every second if a new GameInformation has been added to the GameState and then creates a
     *  message in this case. This will then be sent by the controller.
     */
    @Scheduled(fixedRate = 1000)
    void handler(){
        try{
            if (currentListElement < GameState.getGameStateInstance().getSize() - 1){
                emitter.onNext(
                        ServerSentEvent.<GameInformation>builder(GameState.getGameStateInstance().getElement(currentListElement + 1)
                        ).build()
                );
                currentListElement++;
            }
        } catch (Exception e){
            emitter.onError(e);
        }
    }
}
