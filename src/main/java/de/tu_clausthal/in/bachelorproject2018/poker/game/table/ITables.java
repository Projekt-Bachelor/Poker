package de.tu_clausthal.in.bachelorproject2018.poker.game.table;

import javax.annotation.Nonnull;


/**
 * Interface um die Tische zu verwalten
 */
public interface ITables
{
    /**
     * fügt einen neuen Tisch hinzu
     *
     * @param p_table Tischobjekt
     * @return Objektreferenz auf alle Tische
     */
    @Nonnull
    ITables add( @Nonnull final ITable p_table );

    /**
     * entfernt einen Tisch
     *
     * @param p_table Tischobjekt
     * @return Objektreferenz auf alle Tische
     */
    ITables remove( @Nonnull final ITable p_table );

}
