package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.IMessage;

import java.util.Queue;


public final class CWinEvaluation implements IRoundAction {

    @Override
    public void accept( final Queue<IRoundAction> p_roundactions, final IMessage p_message )
    {
    }

    @Override
    public Boolean apply( final Queue<IRoundAction> p_p_roundactions )
    {
        return false;
    }

}
