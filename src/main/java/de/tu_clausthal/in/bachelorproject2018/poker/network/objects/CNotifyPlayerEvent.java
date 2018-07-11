package de.tu_clausthal.in.bachelorproject2018.poker.network.objects;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import org.springframework.context.ApplicationEvent;

import javax.annotation.Nonnull;

public class CNotifyPlayerEvent extends ApplicationEvent {
    private static final long serialVersionUID = -46265939151948879L;
    private final IPlayer m_player;
    private final String m_message;

    public CNotifyPlayerEvent(Object source, @Nonnull IPlayer p_player, @Nonnull String p_message) {
        super(source);
        m_player = p_player;
        m_message = p_message;
    }

    public IPlayer getPlayer() {
        return m_player;
    }

    public String getMessage() {
        return m_message;
    }
}
