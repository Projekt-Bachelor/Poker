package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import org.springframework.context.ApplicationEventPublisher;

public abstract class IBaseRoundAction implements IRoundAction
{
    protected final ITable m_table;
    protected final ApplicationEventPublisher m_eventPublisher;

    protected IBaseRoundAction(final ITable p_table, ApplicationEventPublisher m_eventPublisher)
    {
        m_table = p_table;
        this.m_eventPublisher = m_eventPublisher;
    }
}
