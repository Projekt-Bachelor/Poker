package de.tu_clausthal.in.bachelorproject2018.poker.network.objects;

import de.tu_clausthal.in.bachelorproject2018.poker.game.cards.Card;

public class CCardJson {

    private final Card m_card;

    private final String m_destination;

    public CCardJson(Card p_card, String p_destination) {
        this.m_card = p_card;
        this.m_destination = p_destination;
    }
}
