package de.tu_clausthal.in.bachelorproject2018.poker.network;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CSessionRegistration {

    @JsonProperty("uuid")
    private UUID m_uuid;

    public CSessionRegistration(){

    }

    public CSessionRegistration(UUID p_uuid){
        this.m_uuid = p_uuid;
    }

    public UUID get(){
        return this.m_uuid;
    }

    public void set(UUID p_uuid){
        m_uuid = p_uuid;
    }
}

