package de.tu_clausthal.in.bachelorproject2018.poker.network.objects;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CChipJson {
    @JsonProperty( "amount" )
    final int m_amount;

    public CChipJson(int p_amount) {
        this.m_amount = p_amount;
    }
}
