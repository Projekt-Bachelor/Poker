package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.CTable;
import org.springframework.context.ApplicationEventPublisher;

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
    PREFLOP,
    BETTINGROUND1,
    FLOP,
    BETTINGROUND2,
    TURN,
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
    public Stream<IRoundAction> factory(@Nonnull final Collection<IPlayer> p_player, CTable table,
                                        ApplicationEventPublisher p_eventPublisher)
    {
        switch ( this )
        {
            case BETTINGROUND1:
            case BETTINGROUND2:
            case BETTINGROUND3:
            case BETTINGROUND4:
                //erstelle das BetRoundObjekt für den Roundstarter
                return Stream.of( new CBetRound(table, table.getGameHub().getPlayerList().get(
                        table.getGameHub().getChipsHandler().getRoundStarter()
                ), p_eventPublisher));

            case FLOP:
                return Stream.of( new CFlop(table, p_eventPublisher) );

            case PREFLOP:
                return Stream.of( new CPreFlop(table, p_eventPublisher) );

            case TURN:
                return Stream.of( new CRiver(table, p_eventPublisher) );

            case RIVER:
                return Stream.of( new CRiver(table, p_eventPublisher) );

            case WINEVALUATION:
                return Stream.of( new CWinEvaluation(table, p_eventPublisher) );

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
