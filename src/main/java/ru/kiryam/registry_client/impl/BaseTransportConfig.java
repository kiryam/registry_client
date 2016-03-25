package ru.kiryam.registry_client.impl;

import ru.kiryam.registry_client.TransportConfig;

import java.net.URI;

/**
 * Created by kiryam on 25/03/16.
 */
public class BaseTransportConfig implements TransportConfig {
    private final String scheme;
    private final String host;
    private final int port;

    public BaseTransportConfig(URI baseUri) {
        this.scheme = baseUri.getScheme();
        this.host = baseUri.getHost();
        this.port = baseUri.getPort();
    }

    @Override
    public String getScheme() {
        return scheme;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }
}
