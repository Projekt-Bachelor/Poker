package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Enum, um die einzelnen Aktionen für die Runden zu definieren.
 * Jedes Enum-Item enthält ein IRoundAction Objekt, in dem die Funktionalität,
 * festgelegt wird, der Enum selbst liefert die Reihenfolge der Aktionen
 *
 * @todo Gewinn-Ausführung muss noch implementiert werden
 */
public enum ERound implements Supplier<List<IRoundAction>>
{
    BETTINGROUND1(),
    FLOP( new CFlop() ),
    BETTINGROUND2( new CBetRound()),
    TRURN( new CRiver() ),
    BETTINGROUND3(new CBetRound()),
    RIVER( new CRiver() ),
    BETTINGROUND4(new CBetRound()),
    WINEVALUATION(new CWinEvaluation());



    private Stream<IRoundAction> generate( int tischsize )
    {
        switch ( this )
        {
            case BETTINGROUND4:
            case BETTINGROUND3:
            case BETTINGROUND2:
            case BETTINGROUND1:
                return IntStream.range( 0, tischsize ).boxed().map( i -> new CBetRound() );

            case FLOP:
                return Stream.of( new CFlop() );

        }
    }

    /**
     * liefert zu dem aktuellen Enum-Item das Rundenobjekt
     *
     * @return Rundenobjekt
     */
    @Nonnull
    @Override
    public List<IRoundAction> get( int tischsize )
    {
        return Arrays.stream( ERound.values() )
              .flatMap( i -> i.generate( tischsize ) ).collect( Collectors.toList() );

    }
}
