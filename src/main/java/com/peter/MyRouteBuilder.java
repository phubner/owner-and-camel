package com.peter;

import org.aeonbits.owner.ConfigCache;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        // Owner config
        ServerConfig conf = ConfigCache.getOrCreate(ServerConfig.class);

        // Main route
        from("timer://foo?fixedRate=true&period=5s")
                .routeId("test1")
                .log("TEST - myNumber = " + conf.myNumber());

        // Fake route that should not be called
        from("direct:fake")
            .log("This should not happen");
    }

}
