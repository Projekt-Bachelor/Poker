package de.tu_clausthal.in.bachelorproject2018.poker.network.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tu_clausthal.in.bachelorproject2018.poker.game.cards.Card;

public class CCardJson {

    @JsonProperty( "card" )
    private final Card m_card;
    @JsonProperty( "destination" )
    private final String m_destination;
    @JsonProperty( "type" )
    private final String m_type;

    public CCardJson(Card p_card, String p_destination, String p_type) {
        this.m_card = p_card;
        this.m_destination = p_destination;
        this.m_type = p_type;
    }
}
