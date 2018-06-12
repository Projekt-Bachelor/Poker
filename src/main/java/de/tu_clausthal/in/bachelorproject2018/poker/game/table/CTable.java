package de.tu_clausthal.in.bachelorproject2018.poker.game.table;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

import javax.annotation.Nonnull;
import java.util.Objects;


/**
 * Spieletisch Definition.
 * Jeder Tisch hat einen eindeutigen Namne, über den er definiert wird,
 * darum muss sowohl hashCode, wie auch equals überladen werden
 *
 * @todo hier muss die List der Spieler hinein und die Logik, was aktuell im Startup ist
 */
public final class CTable implements ITable
{
    /**
     * Name des Tisches
     */
    private final String m_name;

    /**
     * Konstruktor
     *
     * @param p_name name
     */
    public CTable( @Nonnull final String p_name )
    {
        m_name = p_name;
    }

    @Override
    public int hashCode()
    {
        return m_name.hashCode();
    }

    @Override
    public boolean equals( final Object p_object )
    {
        return Objects.nonNull( p_object ) && p_object instanceof ITable && p_object.hashCode() == this.hashCode();
    }

    @Override
    public String toString()
    {
        return m_name;
    }

    @Nonnull
    @Override
    public String name()
    {
        return m_name;
    }

    @Override
    public boolean isactive( @Nonnull final IPlayer p_player )
    {
        return false;
    }

    @Override
    public IPlayer active()
    {
        return null;
    }

    @Override
    public ITable kick( @Nonnull final IPlayer p_player )
    {
        return null;
    }

    @Override
    public boolean hasNext()
    {
        return false;
    }

    @Override
    public IPlayer next()
    {
        return null;
    }
}
