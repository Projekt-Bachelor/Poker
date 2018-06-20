package de.tu_clausthal.in.bachelorproject2018.poker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Applikation
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class CApplication
{
    public static void main( final String[] p_args )
    {
        SpringApplication.run( CApplication.class, p_args );

    }

}
