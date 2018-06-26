package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.IMessage;

import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Function;


/**
 * Interface, um die einzelnen Aktionen in einer Runde zu definieren.
 * Es ist einmal eine Funktion, in die die aktuelle Ausfühungsliste hinein geht und ein Boolean als Ergebnis geliefert wird,
 * um die Aktion aus der Queue zu entfernen und es ist ein BiConsumer, der ebenfalls die aktuelle Ausfühungsqueue bekommt und die eingehende Nachricht
 */
public interface IRoundAction extends Function<Queue<IRoundAction>, Boolean>, BiConsumer<Queue<IRoundAction>, IMessage>
{
}
