package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.EGamestateManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CGameMessage;
import org.pmw.tinylog.Logger;

import javax.annotation.Nonnull;


/**
 * Call-Aktion
 */
public class CCall extends IBaseAction
{


    public CCall(ITable p_table) {
        super(p_table);
    }

    @Override
    /**
     * Überprüft ob der Spieler callen kann
     * Berechnet den Wert, der fürs Callen benötigt wird
     * Setzt den Wert
     */
    public void accept( @Nonnull final IPlayer p_player )
    {
        int callAmount;
        // Überprüfung, ob der Spieler überhaupt callen kann
        if ( p_player.getAmountBetThisRound() - m_table.getGameHub().getChipsHandler().getHighestBidThisRound() > 0 )
            throw new RuntimeException( "Spieler kannst nicht callen" );
        //Wert berechnen, wie viel der Spieler noch zum Callen bezahlen muss
        callAmount = m_table.getGameHub().getChipsHandler().getHighestBidThisRound()-p_player.getAmountBetThisRound();
        //AmountBetThisRound updaten
        p_player.addToAmountBetThisRound(callAmount);
        p_player.substractChips(callAmount);

        //callAmount zum Pot hinzufügen
        m_table.getGameHub().getChipsHandler().addToPot(callAmount, p_player.getAmountBetThisRound());

        Logger.info(p_player.getName() + " hat gecallt um folgenden Wert zu erreichen " +
                m_table.getGameHub().getChipsHandler().getHighestBidThisRound() + "! Dabei musste er folgendes setzen: " +
                callAmount);

        EGamestateManagement.INSTANCE.apply(m_table.name()).addGameMessage(
                new CGameMessage(p_player.getName() + " hat gecallt um folgenden Wert zu erreichen " +
                        m_table.getGameHub().getChipsHandler().getHighestBidThisRound() + "! Dabei musste er folgendes setzen: " +
                        callAmount , m_table));

        EGamestateManagement.INSTANCE.apply(m_table.name()).addGameMessage(
                new CGameMessage("Der Pot beträgt jetzt " + m_table.getGameHub().getChipsHandler().getPot() , m_table));
    }
}
