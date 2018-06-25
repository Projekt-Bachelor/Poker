package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum EWinCheck implements Supplier<IBaseWinCheckAction> {
    PREPAREFORWINCHECK (new CPrepareForWinCheck()),
    MULTIPLES (new CMultiples()),
    STRAIGTH (new CStraight()),
    FLUSH (new CFlush());

    /**
     * Wincheckaktions Objekt, das pro Enum-Item definiert ist
     */
    private final IWinCheckAction m_action;

    /**
     * privater Konstruktor des Enum
     *
     * @param p_action WincheckObjekt
     */
    EWinCheck( @Nonnull final IWinCheckAction p_action )
    {
        m_action = p_action;
    }

    /**
     * liefert zu dem aktuellen Enum-Item das WincheckObjekt
     *
     * @return WincheckObjekt
     */
    @Nonnull
    @Override
    public IWinCheckAction get()
    {
        return m_action;
    }
}
