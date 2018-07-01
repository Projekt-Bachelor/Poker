package de.tu_clausthal.in.bachelorproject2018.poker.network;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import org.springframework.context.ApplicationEvent;

public class CGameInformationEvent extends ApplicationEvent {
    private String m_message;
    private ITable m_table;

    public CGameInformationEvent(Object source, String p_message, ITable p_table) {
        super(source);
        this.m_message = p_message;
        this.m_table = p_table;
    }

    public String getMessage(){
        return m_message;
    }

    public ITable getTable(){
        return m_table;
    }
}
