package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.cards.Card;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.EGamestateManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CCardMessage;
import org.pmw.tinylog.Logger;

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

            Card l_card1 = m_table.getGameHub().getCardDealer().getDeck().removeTopCard();
            player.getPlayerhand().takeCard(l_card1);
            EGamestateManagement.INSTANCE.apply(m_table.name()).addCardMessage(
                    new CCardMessage(l_card1, player.getName(), null, m_table, player));

            Card l_card2 = m_table.getGameHub().getCardDealer().getDeck().removeTopCard();
            player.getPlayerhand().takeCard(l_card2);
            EGamestateManagement.INSTANCE.apply(m_table.name()).addCardMessage(
                    new CCardMessage(l_card2, player.getName(), null, m_table, player));

            //m_eventPublisher.publishEvent(new CNotifyPlayerEvent(this, player, player.getPlayerhand().showHand()));
            Logger.info(player.getPlayerhand().showHand());

            //TODO - Send Card-Objects to Player!
        }
        Logger.info("Preflop abgeschlossen!");

        return false;
    }
}
