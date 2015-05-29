package com.peter;

import org.aeonbits.owner.Config;

/**
 * Created by phubner on 5/27/15.
 */

/**
 * Config.Sources is a list of the paths to search for
 * your properties. They are heirachical in nature so the first
 * "file" that contains the requested properties will win
 */
@Config.Sources({"classpath:server.properties" })
public interface ServerConfig extends Config {
    @DefaultValue("${FOO_ENV}")
    int maxThreads();
}