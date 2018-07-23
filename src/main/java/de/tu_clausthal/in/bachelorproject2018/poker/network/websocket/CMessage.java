package de.tu_clausthal.in.bachelorproject2018.poker.network.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Arrays;


/**
 * test socket
 */
@Service
public final class CMessage
{
    /**
     * socket
     */
    private final SimpMessagingTemplate m_websocket;

    /**
     * ctor
     *
     * @param p_websocket socket connection
     */
    @Autowired
    public CMessage( final SimpMessagingTemplate p_websocket )
    {
        this.m_websocket = p_websocket;
    }

    /**
     * sends data
     *
     * @param p_to target location
     * @param p_data data objects
     * @todo print muss entfernt werden
     */
    public final void sendto( @Nonnull final String p_to, @Nonnull final Object... p_data )
    {
        System.out.println( m_websocket + "    " + Arrays.toString( p_data ) );
        Arrays.stream( p_data )
              .forEach( i -> m_websocket.convertAndSend( p_to, i ) );
    }


}
