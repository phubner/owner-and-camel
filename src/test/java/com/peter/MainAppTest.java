package com.peter;

import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by phubner on 7/24/15.
 */
public class MainAppTest extends CamelTestSupport {

    @Override
    public boolean isUseAdviceWith() {
        // tell we are using advice with, which allows us to advice the route
        // before Camel is being started, and thus can replace activemq with something else.
        return true;
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        RouteBuilder routeBuilder = new MyRouteBuilder();
        return routeBuilder;
    }

    @Test
    public void testAdvised() throws Exception {
        // advice the first route using the inlined route builder
        context.getRouteDefinition("test1").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                // intercept sending to mock:foo and do something else
                interceptSendToEndpoint("direct:fake")
                        .skipSendToOriginalEndpoint()
                        .log("In intercepted")
                        .to("mock:advised");
            }
        });

        getMockEndpoint("mock:advised").expectedMessageCount(1);
    }
}
