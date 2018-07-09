package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public enum EGamestateManagement implements IGamestates, Function<ITable, IGamestate> {

    INSTANCE;

    private Map<String, IGamestate> m_gamestates = new ConcurrentHashMap<>();

    @Override
    public IGamestate apply(ITable iTable) {
        final IGamestate l_gamestate = m_gamestates.get(iTable.name());
        if (Objects.isNull(l_gamestate)){
            throw new RuntimeException(MessageFormat.format(
                    "Gamestate des Tisches [{0}] nicht gefunden", iTable.name()));
        }
        return l_gamestate;
    }

    @Nonnull
    @Override
    public void add(@Nonnull IGamestate gamestate) {
        if (m_gamestates.containsKey(gamestate.getTable() ) ){}
            //throw new RuntimeException(MessageFormat.format(
                    //"Gamestate des Tisches [{0}] existiert schon"), gamestate.getTable());
        m_gamestates.put(gamestate.getTable().name(), gamestate);
    }

    @Override
    public void remove(@Nonnull String p_table) {
        //TODO Verbindung zum Backend herstellen
        m_gamestates.remove(p_table);
    }

}
