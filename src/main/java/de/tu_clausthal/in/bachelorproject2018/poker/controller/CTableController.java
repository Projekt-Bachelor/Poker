package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.CPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.CTable;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * REST Controller um einen einzelnen Tisch zu verwalten
 *
 * @todo leave implementieren, aber darauf achten, dass nur der Spieler sich selbst entfernen kann
 * @todo start des Spiels implementieren
 * @todo add return values to all this functions
 */
@RestController
@RequestMapping( "/table" )
public final class CTableController
{

    /**
     * erzeugt einen neuen Tisch
     *
     * @param p_name Name des Tisches
     */
    @RequestMapping( value = "/create/{name}/{owner}" )
    public void create( @PathVariable( "name" ) final String p_name, @PathVariable( "owner" ) final String p_owner )
    {
        // in dem Singleton der Tables wird nun ein neuer Tisch mit einem Namen und einem Besitzer erzeugt
        ETables.INSTANCE.add( new CTable( p_name, new CPlayer( p_owner ) ) );
    }

    /**
     * liefert eine Liste aller Spieler, die aktuell an einem Tisch sitzen
     *
     * @param p_table Tischname
     * @return Liste mit Spielerobjekten
     */
    @RequestMapping( value = "/{table}/players", produces = APPLICATION_JSON_VALUE )
    public Collection<IPlayer> players( @PathVariable( "table" ) final String p_table )
    {
        return ETables.INSTANCE.apply( p_table ).list();
    }

    /**
     * ein Spieler möchte einem Tisch betreten
     *
     * @param p_table Tischname
     * @param p_player Spielername
     */
    @RequestMapping( value = "/join/{table}/{player}" )
    public void join( @PathVariable( "table" ) final String p_table, @PathVariable( "player" ) final String p_player )
    {
        ETables.INSTANCE.apply( p_table ).join( new CPlayer( p_player ) );
    }

}
