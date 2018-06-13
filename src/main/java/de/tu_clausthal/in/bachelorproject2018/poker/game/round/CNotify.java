package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

/**
 * Benachrichtung wie der aktuelle Tisch aussieht
 *
 * @todo Notify implementieren, hierzu muss diese Klasse an den Websocket mit dran
 */
public final class CNotify implements IRoundAction
{
    @Override
    public boolean stop()
    {
        return false;
    }

    @Override
    public IRoundAction get()
    {
        System.out.println("blub");
        return this;
    }
}
