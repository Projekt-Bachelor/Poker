package de.tu_clausthal.in.bachelorproject2018.poker.roundaction;

import java.util.function.Supplier;


public interface IRoundAction extends Supplier<IRoundAction>
{
    /**
     * Runde stoppen nach Ausführung
     *
     * @return
     */
    boolean stop();

}
