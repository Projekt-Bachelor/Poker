package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;

import javax.annotation.Nonnull;


/**
 * Raise Aktion
 */
public final class CRaise implements IAction
{
    /**
     * Wert für einen Raise
     */
    private int value;

    private String player;

    private String table;

    @Override
    public void accept( @Nonnull final IPlayer p_player )
    {
        // Überprüfung, ob der Spieler sich den Raise überhaupt leisten kann
        if ( p_player.amount() - value < 0 )
            throw new RuntimeException( "Raise ist zu hoch" );

        p_player.amount( p_player.amount() + value );
        //ChipsHandling.getInstance().addToPot( value );
    }

    public int getValue() {
        return value;
    }
}
