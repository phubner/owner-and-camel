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

        ServerConfig conf = ConfigFactory
                .create(ServerConfig.class,
                        System.getProperties(),
                        System.getenv());

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
        from("file:src/data?noop=true")
                .log("ENV var " + conf.maxThreads());

    }

}
