package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Nonnull;

@Controller
public class CExceptionHandlingController {

    @ExceptionHandler(RuntimeException.class)
    @SendTo("/queue/error")
    public CError handleRuntimeException(RuntimeException p_ex){
        return new CError(p_ex.getMessage());
    }


    public static final class CError {

        @JsonProperty("error")
        private String m_error;

        public CError(@Nonnull String p_error){
            m_error = p_error;
        }
    }
}
