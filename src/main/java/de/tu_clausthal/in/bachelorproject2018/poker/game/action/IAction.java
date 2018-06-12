package de.tu_clausthal.in.bachelorproject2018.poker.game.action;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

import java.util.function.Consumer;


/**
 * Aktionsinterface, um die einzelnen Aktionen des Spielers abzubilden.
 * Die Aktion ist ein Consumer für einen Spieler, der die Aktion ausführt
 */
public interface IAction extends Consumer<IPlayer>
{
}
