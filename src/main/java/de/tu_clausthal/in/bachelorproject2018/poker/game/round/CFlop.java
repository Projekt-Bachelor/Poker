package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.CardDealer;

/**
 * Flop Ausführung
 *
 * @todo Flop Logik implementieren
 */
public final class CFlop implements IRoundAction
{
    @Override
    public boolean stop()
    {
        return false;
    }

    @Override
    public IRoundAction get()
    {
        // Logik Flop

        CardDealer.getInstance().getTableCards().add(CardDealer.getInstance().getDeck().removeTopCard());
        CardDealer.getInstance().getTableCards().add(CardDealer.getInstance().getDeck().removeTopCard());
        CardDealer.getInstance().getTableCards().add(CardDealer.getInstance().getDeck().removeTopCard());

        return this;
    }
}
