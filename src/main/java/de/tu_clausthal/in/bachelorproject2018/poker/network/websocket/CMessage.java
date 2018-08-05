package de.tu_clausthal.in.bachelorproject2018.poker.network.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.pmw.tinylog.Logger;
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
     * JSON serializer
     */
    private static final ObjectMapper JSONMAPPER = new ObjectMapper();
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
     */
    public final void sendto( @Nonnull final String p_to, @Nonnull final Object... p_data )
    {
        Arrays.stream( p_data )
              .forEach( i -> {
                  try
                  {
                      Logger.info("Sending message: " + JSONMAPPER.writeValueAsString( i ) + " to Endpoint: " + p_to);
                      m_websocket.convertAndSend( p_to, JSONMAPPER.writeValueAsString( i ) );
                  }
                  catch ( final JsonProcessingException l_exception )
                  {
                      Logger.error( l_exception );
                  }
              } );
    }


}
