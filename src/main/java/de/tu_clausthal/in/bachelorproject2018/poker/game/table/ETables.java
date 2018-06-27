package de.tu_clausthal.in.bachelorproject2018.poker.game.table;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Singleton um alle Tische zu verwalten.
 * Es ist einmal ein Supplier, der nur die Namen der Tische liefert und einmal eine Funktion,
 * bei der ich einen String (Name des Tisches) gebe und mir dann das Table-Objekt liefert
 */
public enum ETables implements ITables, Supplier<Set<String>>, Function<String, ITable>
{
    INSTANCE;

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

    @Override
    public ITable apply( final String p_name )
    {
        // falls der Tisch nicht existiert, dann fliegt eine Exception
        final ITable l_table = m_tables.get( p_name );
        if ( Objects.isNull( l_table ) )
            throw new RuntimeException( MessageFormat.format( "Tisch mit dem Namen [{0}] nicht gefunden", p_name ) );

        return l_table;
    }
}
