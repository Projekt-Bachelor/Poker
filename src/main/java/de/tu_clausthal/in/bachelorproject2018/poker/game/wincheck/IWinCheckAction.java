package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

/**
 * Interface, um die einzelnen Teile der WinEvaluation zu definieren
 * Die Aktion ist ein Supplier, der sich selbst liefert
 */
public interface IWinCheckAction// extends Function<PlayerHand, IWinCheckAction>
{

    IWinCheckAction apply(HandStatistic handStatistic);
}
