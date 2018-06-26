package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

import javax.annotation.Nonnull;


/**
 * Raise Aktion
 */
public final class CRaise extends IBaseAction
{
    /**
     * Wert für einen Raise
     */
    private int raiseValue;

    protected CRaise(ITable p_table) {
        super(p_table);
    }

    @Override
    /**
     * Überprüft ob der Spieler raisen kann
     * Führt den Raise durch
     */
    public void accept( @Nonnull final IPlayer p_player )
    {
        // Überprüfung, ob der Spieler sich den Raise überhaupt leisten kann
        if ( p_player.getChipsCount()- raiseValue < 0 )
            throw new RuntimeException( "Raise ist zu hoch" );

        // Überprüfung, ob der Spieler überhaupt raisen kann
        if ( p_player.getAmountBetThisRound() - m_table.getGameHub().getChipsHandler().getHighestBidThisRound() >= 0 )
            throw new RuntimeException( "Spieler kannst nicht raisen" );

        //amountBetThisRound updaten
        p_player.addToAmountBetThisRound(raiseValue);
        p_player.substractChips(raiseValue);
        //Chips dem Pot hinzufügen
        m_table.getGameHub().getChipsHandler().addToPot(raiseValue, p_player.getAmountBetThisRound());
    }

}
