package de.tu_clausthal.in.bachelorproject2018.poker.game.table;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;


/**
 * Singletone um alle Tische zu verwalten
 */
public enum ETables implements ITables, Supplier<Set<String>>
{
    INSTANCES;

    /**
     * thread-safe Map, die den Namen und den Tisch verwaltet
     */
    private final Map<String, ITable> m_tables = new ConcurrentHashMap<>();

    @Nonnull
    @Override
    public ITables add( @Nonnull final ITable p_table )
    {
        // überprüfe, ob Tisch schon existiert
        if ( m_tables.containsKey( p_table.name() ) )
            throw new RuntimeException( MessageFormat.format( "Tisch [{0}] existiert schon", p_table ) );

        // wenn nicht, dann füge ihn hinzu
        m_tables.put( p_table.name(), p_table );

        return this;
    }

    @Override
    public ITables remove( @Nonnull final ITable p_table )
    {
        m_tables.remove( p_table.name() );
        return this;
    }

    @Override
    public Set<String> get()
    {
        return m_tables.keySet();
    }
}
