package com.peter;

import org.aeonbits.owner.Config;

/**
 * Created by phubner on 5/27/15.
 */

@Config.Sources({"classpath:server.properties" })
public interface ServerConfig extends Config {
    @DefaultValue("${FOO_ENV}")
    int maxThreads();
}