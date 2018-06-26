package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.function.Function;
import java.util.function.Supplier;

public enum EWinCheck implements Function<ITable, IWinCheckAction>
{
    PREPAREFORWINCHECK,
    MULTIPLES,
    STRAIGTH,
    FLUSH;

    /**
     * liefert zu dem aktuellen Enum-Item das WincheckObjekt
     *
     * @return WincheckObjekt
     */
    @Override
    public IWinCheckAction apply( final ITable p_table )
    {
        switch ( this )
        {
            case PREPAREFORWINCHECK:
                return new CPrepareForWinCheck( p_table );

            case MULTIPLES:
                return new CMultiples( p_table );

            case STRAIGTH:
                return new CStraight( p_table );

            case FLUSH:
                return new CFlush( p_table );

            default:
                throw new RuntimeException( MessageFormat.format( "win check [{0}] existiert nicht", this ) );
        }
    }
}
