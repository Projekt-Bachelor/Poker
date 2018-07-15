package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public enum EGamestateManagement implements IGamestates, Function<String, IGamestate> {

    INSTANCE;

    private Map<String, IGamestate> m_gamestates = new ConcurrentHashMap<>();

    @Override
    public IGamestate apply(String p_table) {
        final IGamestate l_gamestate = m_gamestates.get(p_table);
        if (Objects.isNull(l_gamestate)){
            throw new RuntimeException(MessageFormat.format(
                    "Gamestate des Tisches [{0}] nicht gefunden", p_table));
        }
        return l_gamestate;
    }

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

    @Override
    public Collection<IGamestate> getGamestates() {
        return m_gamestates.values();
    }

}
