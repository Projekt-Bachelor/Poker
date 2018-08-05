package de.tu_clausthal.in.bachelorproject2018.poker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CRedirectController {

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String sendGameHtml(){
        return "game";
    }

    @RequestMapping(value = "/hub", method = RequestMethod.GET)
    public String sendHubHtml(){
        return "hub";
    }
}
