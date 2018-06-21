package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.IMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

import java.util.Queue;

public class CPreFlop extends IBaseRoundAction {
    protected CPreFlop(ITable p_table) {
        super(p_table);
    }

    @Override
    public void accept(Queue<IRoundAction> iRoundActions, IMessage iMessage) {

    }

    @Override
    public Boolean apply(Queue<IRoundAction> iRoundActions) {
        m_table.getGameHub().getChipsHandler().resetHand();
        return false;
    }
}
