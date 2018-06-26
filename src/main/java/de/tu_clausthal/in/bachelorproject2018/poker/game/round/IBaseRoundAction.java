package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

public abstract class IBaseRoundAction implements IRoundAction
{
    protected final ITable m_table;

    protected IBaseRoundAction( final ITable p_table )
    {
        m_table = p_table;
    }
}
