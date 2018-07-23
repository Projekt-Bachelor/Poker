package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.objects.CMessageEvent;
import org.springframework.lang.NonNull;

public class CGameMessage implements IGamestateMessage{
    @JsonProperty( "text" )
    private final String m_text;
    @JsonProperty( "table" )
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
