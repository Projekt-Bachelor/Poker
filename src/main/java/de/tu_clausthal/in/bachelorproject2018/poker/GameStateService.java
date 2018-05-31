package de.tu_clausthal.in.bachelorproject2018.poker;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.GameInformation;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.util.Date;


@Component
public class GameStateService {

    private final EmitterProcessor<ServerSentEvent<GameInformation>> emitter;

    public GameStateService(){
        emitter = EmitterProcessor.create();
    }

    public Flux<ServerSentEvent<GameInformation>> getGameInformations(){
        return emitter.log();
    }

    //TODO - Get the Information from GameState
    @Scheduled(fixedRate = 1000)
    void handler(){
        try{
            emitter.onNext(
                    ServerSentEvent.builder(
                            new GameInformation(new Date().toString())
                    ).build()
            );
        } catch (Exception e){
            emitter.onError(e);
        }
    }
}
