package de.tu_clausthal.in.bachelorproject2018.poker.network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Service;

@Service
public class CMessageService {

    @Autowired
    SimpMessageSendingOperations messagingTemplate;

    /**
     * Sendet eine Nachricht an eine bestimmte SessionId
     * @param p_sessionId Session Id
     * @param p_message Nachricht die gesendet werden soll
     */
    public void sendMessageToSession(String p_sessionId, String p_message){
        messagingTemplate.convertAndSendToUser(p_sessionId, "/queue/message", p_message, createHeaders(p_sessionId));
    }

    /**
     * Setzt die richtigen Header
     * @param p_sessionId
     * @return Headers
     */
    private MessageHeaders createHeaders(String p_sessionId){
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(p_sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
