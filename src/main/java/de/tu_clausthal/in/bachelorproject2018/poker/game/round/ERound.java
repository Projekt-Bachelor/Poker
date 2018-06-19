package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Enum, um die einzelnen Aktionen für die Runden zu definieren.
 * Jedes Enum-Item enthält ein IRoundAction Objekt, in dem die Funktionalität,
 * festgelegt wird, der Enum selbst liefert die Reihenfolge der Aktionen
 *
 * @todo Gewinn-Ausführung muss noch implementiert werden
 */
public enum ERound
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
    private Stream<IRoundAction> factory( @Nonnull final List<IPlayer> p_player )
    {
        switch ( this )
        {
            case BETTINGROUND1:
            case BETTINGROUND2:
            case BETTINGROUND3:
            case BETTINGROUND4:
                return p_player.stream().map( CBetRound::new );

            case FLOP:
                return Stream.of( new CFlop() );

            case RIVER:
                return Stream.of( new CRiver() );

            case WINEVALUATION:
                return Stream.of( new CWinEvaluation() );

            default:
                throw new RuntimeException( MessageFormat.format( "Runde [{0}] nicht bekannt", this ) );
        }
    }

    /**
     * liefert anhand aller Spieler alle Runden-Objekte
     *
     * @param p_player player list
     * @return Rundenobjekt
     */
    @Nonnull
    public static Stream<IRoundAction> generate( @Nonnull final List<IPlayer> p_player )
    {
        return Arrays.stream( ERound.values() ).flatMap( i -> i.factory( p_player ) );

    }
}
