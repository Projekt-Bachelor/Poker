package de.tu_clausthal.in.bachelorproject2018.poker.network.Tokens;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

/**
 * Diese Klasse entfernt ungenutzte Tokens (Tokens die älter als 5 min sind)
 */
@Component
public class CTokenGarbageService {

    /**
     * Geht alle gespeicherten Tokens durch und kuckt ob diese älter als 5 min sind. Ist das der Fall werden die Tokens
     * aus der Map entfernt!
     */
    @Scheduled(fixedRate = 5000)
    public void removeOldTokens(){
        Set<UUID> l_activeTokens = ETokens.INSTANCE.get();

        for (UUID l_uuid : l_activeTokens){
            if (ETokens.INSTANCE.apply(l_uuid).getValue2().compareTo(
                    new Timestamp(System.currentTimeMillis() + 300000)) > 0){
                ETokens.INSTANCE.remove(l_uuid);
            }
        }
    }
}
