package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

public class CNotifyMessage implements IGamestateMessage {

    private final String m_text;

    private final ITable m_table;

    private final IPlayer m_player;


    public CNotifyMessage(String p_text, ITable p_table, IPlayer p_player) {
        this.m_text = p_text;
        this.m_table = p_table;
        this.m_player = p_player;
    }

    public String getText(){
        return m_text;
    }

    public ITable getTable(){
        return m_table;
    }

    public IPlayer getPlayer(){
        return m_player;
    }
}
