package de.tu_clausthal.in.bachelorproject2018.poker;

import de.tu_clausthal.in.bachelorproject2018.poker.action.IAction;

import java.util.function.Consumer;


public interface IPlayer extends Consumer<IAction>
{

    int amount();

    IPlayer amount( int p_amount );

}
