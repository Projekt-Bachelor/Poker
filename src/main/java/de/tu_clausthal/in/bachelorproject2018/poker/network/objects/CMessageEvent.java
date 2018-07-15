package de.tu_clausthal.in.bachelorproject2018.poker.network.objects;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import org.springframework.context.ApplicationEvent;

public class CMessageEvent extends ApplicationEvent {
    private static final long serialVersionUID = -6702662711312713610L;
    private Object m_message;
    private ITable m_table;

    public CMessageEvent(Object source, Object p_message, ITable p_table) {
        super(source);
        this.m_message = p_message;
        this.m_table = p_table;
    }

    public Object getMessage(){
        return m_message;
    }

    public ITable getTable(){
        return m_table;
    }
}
