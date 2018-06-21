package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.CTable;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;


/**
 * Enum, um die einzelnen Aktionen für die Runden zu definieren.
 * Jedes Enum-Item enthält ein IRoundAction Objekt, in dem die Funktionalität,
 * festgelegt wird, der Enum selbst liefert die Reihenfolge der Aktionen
 *
 * @todo Gewinn-Ausführung muss noch implementiert werden
 */
public enum ERound implements Iterator<ERound>
{
    BETTINGROUND1,
    FLOP,
    BETTINGROUND2,
    TRURN,
    BETTINGROUND3,
    RIVER,
    BETTINGROUND4,
    WINEVALUATION;


    /**
     * factory round objects
     *
     * @param p_player player list
     * @return stream of objects
     */
    public Stream<IRoundAction> factory( @Nonnull final Collection<IPlayer> p_player, CTable table )
    {
        switch ( this )
        {
            case BETTINGROUND1:
            case BETTINGROUND2:
            case BETTINGROUND3:
            case BETTINGROUND4:
                return p_player.stream().map( CBetRound::new );

            case FLOP:
                return Stream.of( new CFlop(table) );

            case RIVER:
                return Stream.of( new CRiver(table) );

            case WINEVALUATION:
                return Stream.of( new CWinEvaluation() );

            default:
                throw new RuntimeException( MessageFormat.format( "Runde [{0}] nicht bekannt", this ) );
        }
    }


    @Override
    public boolean hasNext()
    {
        return this.ordinal() < ERound.values().length;
    }

    @Override
    public ERound next()
    {
        return ERound.values()[ this.ordinal() + 1 ];
    }
}
