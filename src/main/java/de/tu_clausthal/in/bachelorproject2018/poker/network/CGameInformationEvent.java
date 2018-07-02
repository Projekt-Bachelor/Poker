package de.tu_clausthal.in.bachelorproject2018.poker.network;

import org.springframework.context.ApplicationEvent;

public class CGameInformationEvent extends ApplicationEvent {
    private static final long serialVersionUID = -6702662711312713610L;
    private String m_message;
    private String m_table;

    public CGameInformationEvent(Object source, String p_message, String p_table) {
        super(source);
        this.m_message = p_message;
        this.m_table = p_table;
    }

    public String getMessage(){
        return m_message;
    }

    public String getTable(){
        return m_table;
    }
}
