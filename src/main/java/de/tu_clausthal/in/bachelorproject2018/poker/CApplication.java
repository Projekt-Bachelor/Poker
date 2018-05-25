package de.tu_clausthal.in.bachelorproject2018.poker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        SpringApplication.run( CApplication.class, p_args );
    }

}
