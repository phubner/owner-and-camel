package com.peter;

import org.apache.camel.builder.RouteBuilder;
import org.aeonbits.owner.ConfigFactory;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        /**
         * create a factory and tell it what properties to use.
         * In this case we want to grab data from the env as well as
         * what we specified in the ServerConfig class
         *
         * If you want to not use the default defined in server.properties
         * set your env $FOO_ENV to something other than 30
         */
        ServerConfig conf = ConfigFactory
                .create(ServerConfig.class,
                        System.getProperties(),
                        System.getenv());

        /**
         * This sample just uses files as a Producer. The goal
         * is to pull the correct property from the ServerConfig and
         * use it in the route.
         */
        from("file:src/data?noop=true")
                .log("ENV var " + conf.maxThreads());

    }

}
