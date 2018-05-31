package de.tu_clausthal.in.bachelorproject2018.poker;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.Hub;
import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * This controller takes care of the player management.
 */
@Controller
@SessionAttributes("instance")
public class Controller_Player {

    /**
     * This method takes care of sending the HTML form and assigns the form fields to the corresponding variables
     * of the Model class (Player)!
     *
     * @param model
     * @return HTML form with which the player can "register" with a username.
     */
    @RequestMapping(value = "/player", method = RequestMethod.GET)
    public String userForm(Model model){
        model.addAttribute("player", new Player());
        return "player";
    }

    /**
     * This method takes care of displaying the results of the previously completed HTML-form after pressing the Submit button.
     * @param player
     * @param hub
     * @return HTML page showing the user name and all players currently in the game.
     */
    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public String userSubmit(@ModelAttribute Player player, @ModelAttribute("instance") Hub hub){
        hub.addPlayer(player);
        return "result";
    }

    /**
     * This method updates the SessionAttribute(instance or rather Hub) each time the controller is invoked.
     * @return instance of the Singleton Hub.
     */
    @ModelAttribute("instance")
    public Hub refreshHub(){
        return Hub.getHubInstance();
    }
}