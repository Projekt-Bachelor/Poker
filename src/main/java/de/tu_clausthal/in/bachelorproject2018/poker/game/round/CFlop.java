package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.CardDealer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.IMessage;

import java.util.Queue;


/**
 * Flop Ausführung
 *
 * @todo Flop Logik implementieren
 */
public final class CFlop implements IRoundAction
{

    @Override
    public void accept( final Queue<IRoundAction> p_roundactions, final IMessage p_message )
    {
    }

    @Override
    public Boolean apply( final Queue<IRoundAction> p_p_roundactions )
    {
        CardDealer.getInstance().getTableCards().add(CardDealer.getInstance().getDeck().removeTopCard());
        CardDealer.getInstance().getTableCards().add(CardDealer.getInstance().getDeck().removeTopCard());
        CardDealer.getInstance().getTableCards().add(CardDealer.getInstance().getDeck().removeTopCard());

        return false;
    }
}
