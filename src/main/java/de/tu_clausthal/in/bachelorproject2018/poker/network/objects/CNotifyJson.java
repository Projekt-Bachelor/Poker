package de.tu_clausthal.in.bachelorproject2018.poker.network.objects;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CNotifyJson {
    @JsonProperty( "notification" )
    private final String m_text;


    public CNotifyJson(String p_text) {
        m_text = p_text;
    }
}
