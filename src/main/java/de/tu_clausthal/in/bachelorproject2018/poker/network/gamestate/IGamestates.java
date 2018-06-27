package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import javax.annotation.Nonnull;

public interface IGamestates {

    @Nonnull
    void add(@Nonnull final IGamestate gamestate);

    void remove(@Nonnull final String p_table);
}
