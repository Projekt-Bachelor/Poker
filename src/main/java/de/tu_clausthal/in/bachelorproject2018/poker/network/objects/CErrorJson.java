package de.tu_clausthal.in.bachelorproject2018.poker.network.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CErrorJson {

    @JsonProperty("error")
    private final String m_error;

    public CErrorJson(String p_error) {
        this.m_error = p_error;
    }
}
