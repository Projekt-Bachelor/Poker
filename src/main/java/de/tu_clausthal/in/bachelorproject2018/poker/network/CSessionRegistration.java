package de.tu_clausthal.in.bachelorproject2018.poker.network;

import java.util.UUID;

public class CSessionRegistration {

    private UUID m_uuid;

    public UUID get(){
        return this.m_uuid;
    }

    public void set(UUID p_uuid){
        m_uuid = p_uuid;
    }
}
