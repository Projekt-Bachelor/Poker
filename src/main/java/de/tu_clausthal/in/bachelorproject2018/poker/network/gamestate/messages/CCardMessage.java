package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages;

import de.tu_clausthal.in.bachelorproject2018.poker.game.cards.Card;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import org.springframework.lang.NonNull;

public class CCardMessage implements IGamestateMessage{

    private final Card m_card;

    private final String m_destination;

    private final ITable m_table;

    private final IPlayer m_player;

    public CCardMessage(@NonNull Card p_card, @NonNull String p_destination, @NonNull ITable p_table, @NonNull IPlayer p_player) {
        this.m_card = p_card;
        this.m_destination = p_destination;
        this.m_table = p_table;
        this.m_player = p_player;
    }

    public Card getCard(){
        return m_card;
    }

    public String getDestination() {
        return m_destination;
    }

    public ITable getTable(){
        return m_table;
    }

    public IPlayer getPlayer(){
        return m_player;
    }
}
