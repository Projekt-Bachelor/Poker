package de.tu_clausthal.in.bachelorproject2018.poker.network.objects;

import javax.annotation.Nonnull;

public class CNotification {

    private String m_message;

    public CNotification(@Nonnull String p_message){
        m_message = p_message;
    }

    public String getMessage() {
        return m_message;
    }
}
