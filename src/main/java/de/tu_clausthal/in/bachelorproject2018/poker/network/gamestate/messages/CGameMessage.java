package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import org.springframework.lang.NonNull;

public class CGameMessage implements IGamestateMessage{

    private final String m_text;

    private final ITable m_table;

    public CGameMessage(@NonNull String p_text, @NonNull ITable p_table){
        this.m_text = p_text;
        this.m_table = p_table;
    }

    public String getText() {
        return m_text;
    }

    public ITable getTable() {
        return m_table;
    }
}
