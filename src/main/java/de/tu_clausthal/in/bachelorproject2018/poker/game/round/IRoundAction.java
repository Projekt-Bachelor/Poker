package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import java.util.function.Supplier;


/**
 * Interface, um die einzelnen Aktionen in einer Runde zu definieren.
 * Die Aktion ist ein Supplier, der sich selbst liefert
 */
public interface IRoundAction extends Supplier<IRoundAction>
{
    /**
     * Runde stoppen nach Ausführung
     *
     * @return Boolean flag, um die zu stoppen
     */
    boolean stop();

}
