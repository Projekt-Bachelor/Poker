package de.tu_clausthal.in.bachelorproject2018.poker.network;

import de.tu_clausthal.in.bachelorproject2018.poker.game.action.IAction;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

import java.util.function.Supplier;


/**
 * Interface für ein Nachrichtenobjekt das von außen über den Websocket kommt
 * @todo muss in das Kommunikationspaket
 */
public interface IMessage extends Supplier<IAction>
{

    String type();

    Number value();

    ITable table();

    IPlayer player();






}
