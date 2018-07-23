package de.tu_clausthal.in.bachelorproject2018.poker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Applikation
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class CApplication
{

    /**
     * ctor
     *
     * @param p_context application context
     */
    @Autowired
    public CApplication( @Nonnull final ApplicationContext p_context )
    {
        CGlobal.initialize( p_context );
    }

    /**
     * main function
     *
     * @param p_args command line arguments
     */
    public static void main( final String[] p_args )
    {
        SpringApplication.run( CApplication.class, p_args );

    }


    /**
     * global context
     */
    public static final class CGlobal
    {
        /**
         * global instance
         */
        private static AtomicReference<CGlobal> s_instance = new AtomicReference<>();
        /**
         * spring application context
         */
        private final ApplicationContext m_context;

        /**
         * ctor
         *
         * @param p_context context
         */
        private CGlobal( @Nonnull final ApplicationContext p_context )
        {
            m_context = p_context;
        }

        /**
         * returns the application context
         *
         * @return application context
         */
        @Nonnull
        public final ApplicationContext context()
        {
            return m_context;
        }

        /**
         * returns singletone instance
         *
         * @return instance
         */
        @Nonnull
        public static CGlobal instance()
        {
            return s_instance.get();
        }

        /**
         * initialize
         *
         * @param p_context application context
         */
        private static void initialize( @Nonnull final ApplicationContext p_context )
        {
            s_instance.compareAndSet( null, new CGlobal( p_context ) );
        }
    }


}
