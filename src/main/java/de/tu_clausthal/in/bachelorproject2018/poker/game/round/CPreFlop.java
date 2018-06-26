package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;
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
    /**
     * resetet alle wichtigen Methoden im CardDealer und ChipsHandler
     * forciert Blinds der Spieler
     * Teilt Karten an die Spieler aus
     */
    public Boolean apply(Queue<IRoundAction> iRoundActions) {
        //reset for round
        m_table.getGameHub().getChipsHandler().resetHand();
        m_table.getGameHub().getChipsHandler().forceBlinds();
        m_table.getGameHub().getCardDealer().resetForNextRound();

        //deal cards to players
        for (IPlayer player : m_table.getGameHub().getPlayerList()){
            player.getPlayerhand().takeCard(m_table.getGameHub().getCardDealer().getDeck().removeTopCard());
            player.getPlayerhand().takeCard(m_table.getGameHub().getCardDealer().getDeck().removeTopCard());
        }
        return false;
    }
}
