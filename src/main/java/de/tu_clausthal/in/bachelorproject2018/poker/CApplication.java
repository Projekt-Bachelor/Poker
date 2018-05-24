package de.tu_clausthal.in.bachelorproject2018.poker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

/**
 * Applikation
 */
@SpringBootApplication
@EnableAutoConfiguration
public class CApplication
{
    /**
     * main
     *
     * @param p_args arguments
     */
    public static void main( final String[] p_args )
    {
        //commented out, not sure what it does
        //SpringApplication.run( CApplication.class, p_args );


        //testing some stuff
        //create playerhand and deck
        Deck testDeck = new Deck();
        ChipsHandling chipsHandler = new ChipsHandling();
        PlayerHand p1 = new PlayerHand();
        //fill deck with cards
        testDeck.initDeck();
        System.out.println(testDeck.showDeck());
        //shuffle the deck
        testDeck.shuffle();
        System.out.println("Hier wurde gemischt: \n");
        //you can see its shuffled
        System.out.println(testDeck.showDeck());
        //remove topcard from deck, and give it to player
        p1.takeCard(testDeck.removeTopCard());
        p1.takeCard(testDeck.removeTopCard());
        System.out.println("Hier wurde die oberen 2 Karten genommen: \n");
        //last 2 cards are missing
        System.out.println(testDeck.showDeck());
        System.out.println("Der Spieler hat jetzt folgende Karten\n");
        //here are the missing cards
        System.out.println(p1.showHand());

    }

}
