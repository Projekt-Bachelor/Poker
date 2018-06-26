package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

public abstract class IBaseWinCheckAction implements IWinCheckAction{

        protected final ITable m_table;

        protected IBaseWinCheckAction( final ITable p_table )
        {
            m_table = p_table;
        }

}
