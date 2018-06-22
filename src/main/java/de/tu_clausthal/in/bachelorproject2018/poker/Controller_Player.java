package de.tu_clausthal.in.bachelorproject2018.poker;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.CSessionRegistration;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ETables;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Set;

/**
 * This controller takes care of the player management.
 */
@Controller
@SessionAttributes("tables")
public class Controller_Player {

    /**
     * This method takes care of sending the HTML form and assigns the form fields to the corresponding variables
     * of the Model class (CSessionRegistration)!
     *
     * @param model
     * @return HTML form with which the player can "register" with a username.
     */
    @RequestMapping(value = "/player", method = RequestMethod.GET)
    public String playerForm(Model model){
        model.addAttribute("player", new CSessionRegistration());
        return "player";
    }

    /**
     * This method takes care of displaying the results of the previously completed HTML-form after pressing the Submit button.
     * @param player
     * @return HTML page showing the user name and all players currently in the game.
     */
    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public String displayTables(@ModelAttribute CSessionRegistration player, @ModelAttribute("tables") Set<String> tables){
        return "tables";
    }

    /**
     * This method updates the SessionAttribute tables each time the controller is invoked.
     * @return List of all tables.
     */
    @ModelAttribute("tables")
    public Set<String> getTables(){
        return ETables.INSTANCE.get();
    }
}
