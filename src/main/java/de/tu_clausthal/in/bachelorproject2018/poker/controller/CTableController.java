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
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Collection;
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
    public ModelAndView create(@PathVariable( "getName" ) final String p_table, @PathVariable( "owner" ) final String p_owner,
                               ModelMap model)
    {
        // in dem Singleton der Tables wird nun ein neuer Tisch mit einem Namen und einem Besitzer erzeugt
        ETables.INSTANCE.add( new CTable(p_table, p_owner) );

        UUID l_uuid = UUID.randomUUID();
        ETokens.INSTANCE.add(l_uuid, p_table, p_owner, new Timestamp(System.currentTimeMillis()));

        model.addAttribute("uuid", l_uuid);
        return new ModelAndView("redirect:/game1", model);
    }

    /**
     * ein Spieler möchte einem Tisch betreten
     *
     * @param p_table Tischname
     * @param p_player Spielername
     */
    @RequestMapping( value = "/join/{table}/{player}" )
    public ModelAndView join( @PathVariable( "table" ) final String p_table, @PathVariable( "player" ) final String p_player,
                              ModelMap model)
    {
        ETables.INSTANCE.apply( p_table ).join( p_player );

        UUID l_uuid = UUID.randomUUID();
        ETokens.INSTANCE.add(l_uuid, p_table, p_player, new Timestamp(System.currentTimeMillis()));

        model.addAttribute("uuid", l_uuid);
        return new ModelAndView("redirect:/game", model);
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
}
