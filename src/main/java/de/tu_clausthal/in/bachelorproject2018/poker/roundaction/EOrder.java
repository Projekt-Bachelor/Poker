package de.tu_clausthal.in.bachelorproject2018.poker.roundaction;

import java.util.function.Supplier;


public enum EOrder implements Supplier<IRoundAction>
{
    FLOP(  new CFlop() ),
    TABLENOTIFY1( new CTableNotify() ),
    TRURN( new CRiver() ),
    TABLENOTIFY2( new CTableNotify() ),
    RIVER( new CRiver() );
    // Gewinn Ausführung

    private final IRoundAction m_action;

    EOrder( final IRoundAction p_action )
    {
        m_action = p_action;
    }

    @Override
    public IRoundAction get()
    {
        return m_action;
    }
}
