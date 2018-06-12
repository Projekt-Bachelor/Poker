package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * REST Controller um alle Tische zu verwalten
 */
@RestController
@RequestMapping( "/tables" )
public final class CTablesController
{

    /**
     * Aufruf um eine Liste aller Tischnamen zu erhalten
     *
     * @return Tischliste
     */
    @RequestMapping( value = "/list", produces = APPLICATION_JSON_VALUE )
    public Set<String> list()
    {
        return ETables.INSTANCE.get();
    }

}
