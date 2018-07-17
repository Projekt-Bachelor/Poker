package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.*;
import org.springframework.lang.NonNull;

public interface IGamestate {

    @NonNull
    ITable getTable();

    void addGameMessage(@NonNull CGameMessage p_gameMessage);

    void addCardMessage(@NonNull CCardMessage p_cardMessage);

    void addChipMessage(@NonNull CChipMessage p_chipMessage);

    void addNotifyMessage(@NonNull CNotifyMessage p_notifyMessage);

    @NonNull
    IGamestateMessage getSpecificMessage(@NonNull final int index);

    @NonNull
    Number getActualIndex();
}
