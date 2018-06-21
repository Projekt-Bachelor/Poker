package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

public abstract class IBaseAction implements IAction{

        protected final ITable m_table;

        protected IBaseAction( final ITable p_table )
        {
            m_table = p_table;
        }

}
