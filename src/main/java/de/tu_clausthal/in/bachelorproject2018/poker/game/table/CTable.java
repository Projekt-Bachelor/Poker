package de.tu_clausthal.in.bachelorproject2018.poker.game.table;

import de.tu_clausthal.in.bachelorproject2018.poker.game.hubs.GameHub;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.CPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.round.ERound;
import de.tu_clausthal.in.bachelorproject2018.poker.game.round.IRoundAction;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Spieletisch Definition.
 * Jeder Tisch hat einen eindeutigen Namne, über den er definiert wird,
 * darum muss sowohl hashCode, wie auch equals überladen werden
 */
public final class CTable implements ITable
{
    /**
     * Zuordnung des Gamehubs
     */
    private final GameHub gameHub;
    /**
     * Name des Tisches
     */
    private final String m_name;
    /**
     * Spielerliste, hier als LinkedHashMap abgelegt, damit eine Reihenfolge (Linked)
     * festgelegt wird und gleichzeitig aber in einem Set auf Duplikate geprüft werden (jeder Spieler darf nur einmal am Tisch existieren),
     * und das ganze noch synchronized, weil wir in einer verteilten Anwendung arbeiten und ggf 2 Spieler gleichzeitig joinen können
     */
    private Map<String, IPlayer> m_players = Collections.synchronizedMap( new LinkedHashMap<>() );
    /**
     * Besitzer des Spiels, der das SPiel starten kann
     */
    private final IPlayer m_owner;
    /**
     * Queue für die Spielelogik Ausführung (Queue mit IRoundAction-Objekten)
     */
    private final Queue<IRoundAction> m_execution = new ConcurrentLinkedQueue<>();
    /**
     *
     */
    private final AtomicReference<ERound> m_currentround = new AtomicReference<>();

    /**
     * Konstruktor
     *
     * @param p_name getName
     */
    public CTable( @Nonnull final String p_name, @Nonnull final String p_owner)
    {
        m_name = p_name;
        m_owner = new CPlayer( p_owner, this );
        m_players.put( m_owner.getName(), m_owner );
        gameHub = new GameHub(this);

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
        return Objects.nonNull( m_currentround.get() );
    }

    @Nonnull
    @Override
    public ITable start( @Nonnull final IPlayer p_owner )
    {
        // prüfe ob Spiel schon gestartet wurde
        if ( Objects.nonNull( m_currentround.get() ) )
            throw new RuntimeException( MessageFormat.format( "Spiel [{0}] wurde gestartet", m_name ) );
        // nur der Spielebesitzer kann es starten
        /*if ( !m_owner.equals( p_owner ) )
            throw new RuntimeException( "Nur der Besitzer des Spiels kann das Spiel starten" );*/

        // startet Spiel
        this.gameHub.startGame();
        this.generateround();

        return this;
    }

    @Nonnull
    @Override
    public ITable leave( @Nonnull final IPlayer p_player )
    {
        // Spieler muss immer aus dem Set entfernt werden
        m_players.remove( p_player.getName(), p_player );
        return this;
    }

    @Nonnull
    @Override
    public ITable join( @Nonnull final String p_player )
    {
        final IPlayer l_player = new CPlayer( p_player, this );
        if ( Objects.nonNull( m_currentround.get() ) )
            throw new RuntimeException( "Spiel läuft bereits" );
        if ( m_players.containsKey( l_player.getName() ) )
            throw new RuntimeException( "Spieler sitzt bereits am Tisch" );

        m_players.put( l_player.getName(), l_player );
        return this;
    }

    @Override
    public Collection<IPlayer> list()
    {
        return m_players.values();
    }

    @Override
    public String owner() {
        return m_owner.getName();
    }

    @Override
    public GameHub getGameHub() {
        return gameHub;
    }

    /**
     * führe ein Element aus der
     * @param p_message Eingangsnachricht
     */
    @Override
    public void accept( final IMessage p_message )
    {
        // holen den Kopf der Queue and gebe die Nachricht mit der aktuellen Ausführungsqueue weiter
        m_execution.remove().accept( m_execution, p_message );
        this.postaction();
    }

    /**
     * Methode um eine neue Runde zu bauen
     */
    private void generateround()
    {
        // beachte, wenn noch m_currentround null enthält, dass wird mit der inneren Funktion der Startwert gesetzt
        final ERound l_round = m_currentround.updateAndGet( i -> Objects.isNull( i ) ? ERound.values()[0] : i );

        // Runden-Daten erzeugen
        l_round.factory( m_players.values(), this ).forEach( m_execution::add );

        // Ausführung der Runde beginnen
        this.executestep();
    }

    /**
     * führt immer einen Schritt des Spiels aus
     */
    private void executestep()
    {
        // den Head der Queue ausführen und wenn false geliefert, muss auf eine Nachricht gewartet werden
        if ( !m_execution.element().apply( m_execution ) )
            return;

        // ... wenn true geliefert wird entfernen
        m_execution.remove();
        this.postaction();
    }

    /**
     * Methode, die nach jeder Ausführung einer Round-Action durchlaufen wird, um das Spiel ggf fortzusetzen
     */
    private void postaction()
    {
        // wenn Queue nicht leer ist, dann nächste Action ausführen
        if ( !m_execution.isEmpty() )
        {
            this.executestep();
            return;
        }

        // wenn Queue leer ist, dann prüfen, ob es noch eine nächste Runde gibt
        if ( m_currentround.getAndUpdate( i -> i.hasNext() ? i.next() : null ).hasNext() )
            this.generateround();
    }


}
