package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import javax.annotation.Nonnull;
import java.util.function.Supplier;


/**
 * Enum, um die einzelnen Aktionen für die Runden zu definieren.
 * Jedes Enum-Item enthält ein IRoundAction Objekt, in dem die Funktionalität,
 * festgelegt wird, der Enum selbst liefert die Reihenfolge der Aktionen
 *
 * @todo Gewinn-Ausführung muss noch implementiert werden
 */
public enum ERound implements Supplier<IRoundAction>
{
    BETTINGROUND1( new CBetRound()),
    FLOP( new CFlop() ),
    BETTINGROUND2( new CBetRound()),
    TRURN( new CRiver() ),
    BETTINGROUND3(new CBetRound()),
    RIVER( new CRiver() ),
    BETTINGROUND4(new CBetRound()),
    WINEVALUATION(new CWinEvaluation());

    /**
     * Rundenaktions Objekt, das pro Enum-Item definiert ist
     */
    private final IRoundAction m_action;

    /**
     * privater Konstruktor des Enum
     *
     * @param p_action Rundenobjekt
     */
    ERound( @Nonnull final IRoundAction p_action )
    {
        m_action = p_action;
    }

    /**
     * liefert zu dem aktuellen Enum-Item das Rundenobjekt
     *
     * @return Rundenobjekt
     */
    @Nonnull
    @Override
    public IRoundAction get()
    {
        return m_action;
    }
}
