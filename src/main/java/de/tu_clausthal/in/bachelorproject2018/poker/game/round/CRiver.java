package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

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

        return this;
    }
}
