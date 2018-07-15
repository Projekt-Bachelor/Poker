package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import org.springframework.lang.NonNull;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface IGamestates {

    void add(@Nonnull final IGamestate gamestate);

    void remove(@Nonnull final String p_table);

    @NonNull
    Collection<IGamestate> getGamestates();
}
