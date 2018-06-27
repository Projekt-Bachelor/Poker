package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

import javax.annotation.Nonnull;

public interface IGamestate {

    @Nonnull
    ITable getTable();

    void addGameinformation(@Nonnull CGameInformation gameInformation);

    @Nonnull
    CGameInformation getSpecificInformation(@Nonnull final int index);

    @Nonnull
    Number getActualIndex();
}
