package de.tu_clausthal.in.bachelorproject2018.poker.game.table;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


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
     * Spielerliste, hier als LinkedHashSet abgelegt, damit eine Reihenfolge (Linked)
     * festgelegt wird und gleichzeitig aber in einem Set auf Duplikate geprüft werden (jeder Spieler darf nur einmal am Tisch existieren),
     * und das ganze noch synchronized, weil wir in einer verteilten Anwendung arbeiten
     */
    private final Set<IPlayer> m_players = Collections.synchronizedSet( new LinkedHashSet<>() );
    /**
     * eine Liste für die die noch aktiven Spieler enthält, ebenfalls thread-safe;
     */
    private final List<IPlayer> m_round = Collections.synchronizedList( new ArrayList<>() );
    /**
     * Besitzer des Spiels, der das SPiel starten kann
     */
    private final IPlayer m_owner;
    /**
     * Variable ob das Spiel gestartet wurde
     */
    private final AtomicBoolean m_playing = new AtomicBoolean();
    /**
     * einen thread-sicheren Counter, der immer auf den aktuellen Spieler der Runde zeigt
     */
    private final AtomicInteger m_active = new AtomicInteger();

    /**
     * Konstruktor
     *
     * @param p_name name
     */
    public CTable( @Nonnull final String p_name, @Nonnull final IPlayer p_owner)
    {
        m_name = p_name;
        m_owner = p_owner;
        m_players.add( m_owner );
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
    public boolean isplaying()
    {
        return m_playing.get();
    }

    @Nonnull
    @Override
    public ITable start( @Nonnull final IPlayer p_owner )
    {
        // prüfe ob Spiel schon gestartet wurde
        if ( m_playing.get() )
            throw new RuntimeException( MessageFormat.format( "Spiel [{0}] wurde gestartet", m_name ) );
        // nur der Spielebesitzer kann es starten
        if ( !m_owner.equals( p_owner ) )
            throw new RuntimeException( "Nur der Besitzer des Spiels kann das Spiel starten" );

        // setze internes Flag um, dass das SPiel läuft
        m_playing.set( true );

        // um nun den Tisch zu erzeugen, kopiere alle Element aus dem Set in die Liste
        m_round.addAll( m_players );
        return this;
    }

    @Override
    public boolean isactive( @Nonnull final IPlayer p_player )
    {
        return p_player.equals( m_round.get( m_active.get() ) );
    }

    @Nonnull
    @Override
    public ITable leave( @Nonnull final IPlayer p_player )
    {
        // Spieler muss immer aus dem Set entfernt werden
        m_players.remove( p_player );
        // falls das Spiel gestartet wurde, muss er auch aus der aktuellen Runde entfernt werden
        m_round.remove( p_player );
        return this;
    }

    @Nonnull
    @Override
    public ITable join( @Nonnull final IPlayer p_player )
    {
        if ( m_playing.get() )
            throw new RuntimeException( "Spiel läuft bereits" );
        if ( m_players.contains( p_player ) )
            throw new RuntimeException( "Spieler sitzt bereits am Tisch" );

        m_players.add( p_player );
        return this;
    }

    @Override
    public Collection<IPlayer> list()
    {
        return m_players;
    }


    @Override
    public boolean hasNext()
    {
        // @todo noch mal prüfen, wann hier "Schluss" ist
        return m_players.size() > 1;
    }

    @Override
    public IPlayer next()
    {
        return m_round.get(
            (
                // der Integer Wert muss um 1 hochgezählt werden und modulo der Spieler, die noch
                // am Tisch sitzen genommen werden und dann wieder in die Variable gesetzt werden
                m_active.getAndUpdate(
                    i -> (i + 1 ) % m_round.size()
                )

                // da wir aber die Methode getAnd... heisst, wird der Wert vor dem setzen geliefert, d.h. auf diesen müssen wir auch
                // die gleiche Rechnung anwenden, damit wir "den nächsten" Spieler bekommen, der an der Reihe ist
                + 1
            ) % m_round.size()  );
    }
}
