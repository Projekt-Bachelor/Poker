package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.CTable;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import de.tu_clausthal.in.bachelorproject2018.poker.network.Tokens.ETokens;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * REST Controller um einen einzelnen Tisch zu verwalten
 *
 * @todo leave implementieren, aber darauf achten, dass nur der Spieler sich selbst entfernen kann
 * @todo start des Spiels implementieren
 * @todo add return values to all this functions
 */
@Controller
@RestController
@RequestMapping( "/table" )
public final class CTableController
{
    /**
     * erzeugt einen neuen Tisch
     *
     * @param p_table Name des Tisches
     */
    @RequestMapping( value = "/create/{getName}/{owner}" )
    public UUID create(@PathVariable( "getName" ) final String p_table, @PathVariable( "owner" ) final String p_owner,
                               ModelMap model)
    {
        // in dem Singleton der Tables wird nun ein neuer Tisch mit einem Namen und einem Besitzer erzeugt
        ETables.INSTANCE.add( new CTable(p_table, p_owner) );
        return ETokens.INSTANCE.add( UUID.randomUUID(), p_table, p_owner, new Timestamp(System.currentTimeMillis()));

    }

    /**
     * ein Spieler möchte einem Tisch betreten
     *
     * @param p_table Tischname
     * @param p_player Spielername
     */
    @RequestMapping( value = "/join/{table}/{player}" )
    public UUID join( @PathVariable( "table" ) final String p_table, @PathVariable( "player" ) final String p_player,
                              ModelMap model)
    {
        ETables.INSTANCE.apply( p_table ).join( p_player );

        UUID l_uuid = UUID.randomUUID();
        ETokens.INSTANCE.add(l_uuid, p_table, p_player, new Timestamp(System.currentTimeMillis()));

        return l_uuid;
    }

    /**
     * liefert eine Liste aller Spieler, die aktuell an einem Tisch sitzen
     *
     * @param p_table Tischname
     * @return Liste mit Spielerobjekten
     */
    @RequestMapping( value = "/{table}/players", produces = APPLICATION_JSON_VALUE )
    public Collection<String> players( @PathVariable( "table" ) final String p_table )
    {
        List<String> playerNames = new ArrayList<>();
        Collection<IPlayer> players = ETables.INSTANCE.apply( p_table ).list();
        for (IPlayer player : players){
            playerNames.add(player.getName());
        }
        return playerNames;
    }
}
