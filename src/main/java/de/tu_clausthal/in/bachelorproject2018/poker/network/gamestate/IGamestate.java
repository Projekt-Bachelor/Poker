package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CCardMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CChipMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CGameMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.IGamestateMessage;
import org.springframework.lang.NonNull;

import javax.annotation.Nonnull;

public interface IGamestate {

    @Nonnull
    ITable getTable();

    void addGameMessage(@Nonnull CGameMessage p_gameMessage);

    void addCardMessage(@NonNull CCardMessage p_cardMessage);

    void addChipMessage(@NonNull CChipMessage p_chipMessage);

    @Nonnull
    IGamestateMessage getSpecificMessage(@Nonnull final int index);

    @Nonnull
    Number getActualIndex();
}
