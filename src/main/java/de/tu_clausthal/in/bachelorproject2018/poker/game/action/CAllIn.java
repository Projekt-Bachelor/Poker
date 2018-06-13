package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

import javax.annotation.Nonnull;


/**
 * All-In Aktion
 *
 * @todo Anbindung an den Chipshandler muss implementiert werden
 */
public final class CAllIn implements IAction
{
    @Override
    public void accept( @Nonnull final IPlayer p_player )
    {
        // ChipsHandling.getInstance().addToPot( p_player.amount() );
        p_player.amount( 0 );
    }
}
