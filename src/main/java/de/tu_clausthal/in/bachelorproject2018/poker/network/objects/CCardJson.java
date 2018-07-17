package de.tu_clausthal.in.bachelorproject2018.poker.network.objects;

import de.tu_clausthal.in.bachelorproject2018.poker.game.cards.Card;

public class CCardJson {

    private final Card m_card;

    private final String m_destination;

    public CCardJson(Card m_card, String m_destination) {
        this.m_card = m_card;
        this.m_destination = m_destination;
    }
}
