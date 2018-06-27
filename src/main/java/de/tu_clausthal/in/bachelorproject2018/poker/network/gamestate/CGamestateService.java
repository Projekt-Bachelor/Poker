package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Service;

@Service
public class CGamestateService {

    @Autowired
    SimpMessageSendingOperations messagingTemplate;

    public void sendGamestateToSession(String p_sessionId, CGameInformation p_gameinformation){
        messagingTemplate.convertAndSendToUser(
                p_sessionId, "/queue/gamestate", p_gameinformation, createHeaders(p_sessionId));
    }

    private MessageHeaders createHeaders(String p_sessionId){
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(p_sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
