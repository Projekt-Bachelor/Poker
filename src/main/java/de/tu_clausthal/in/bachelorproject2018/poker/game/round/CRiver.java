package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.CardDealer;

/**
 * River-Ausführung
 *
 * @todo River-Logik implementieren
 */
public final class CRiver implements IRoundAction
{

    @Override
    public boolean stop()
    {
        return false;
    }

    @Override
    public IRoundAction get()
    {
        // Logik River
        CardDealer.getInstance().getTableCards().add(CardDealer.getInstance().getDeck().removeTopCard());
        return this;
    }
}
