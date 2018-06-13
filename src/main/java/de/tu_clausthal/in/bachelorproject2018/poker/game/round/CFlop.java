package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

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
        /*
        tableCards.add(playDeck.removeTopCard());
        tableCards.add(playDeck.removeTopCard());
        tableCards.add(playDeck.removeTopCard());
        */
        return this;
    }
}
