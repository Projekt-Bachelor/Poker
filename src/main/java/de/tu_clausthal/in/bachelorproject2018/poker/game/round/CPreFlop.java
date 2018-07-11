package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;
import org.pmw.tinylog.Logger;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Queue;

public class CPreFlop extends IBaseRoundAction {

    protected CPreFlop(ITable p_table, ApplicationEventPublisher m_eventPublisher) {
        super(p_table, m_eventPublisher);
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

            //m_eventPublisher.publishEvent(new CNotifyPlayerEvent(this, player, player.getPlayerhand().showHand()));
            Logger.info(player.getPlayerhand().showHand());

            //TODO - Send Card-Objects to Player!
        }
        Logger.info("Preflop abgeschlossen!");

        return false;
    }
}
