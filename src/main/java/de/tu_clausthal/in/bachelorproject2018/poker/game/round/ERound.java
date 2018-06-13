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
    // erste Runde ist ein "Flop" und über den Parameter wird das passende Rundenobjekt erzeugt
    FLOP( new CFlop() ),
    // zweite Runde ist eine Benachrichtungsrunde, wie der Tisch aussieht
    TABLENOTIFY1( new CNotify() ),
    // dritte Runde, ist die River-Runde
    TRURN( new CRiver() ),
    // vierte Runde ist werden wieder alle Spieler über die Änderung des Tisches benachrichtigt
    TABLENOTIFY2( new CNotify() ),
    // fünfte Runde ist die River-Runde
    RIVER( new CRiver() );
    // sechste ist die Gewinn Ausführung

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
