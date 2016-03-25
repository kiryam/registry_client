package ru.kiryam.registry_client;

/**
 * Created by kiryam on 25/03/16.
 */
public interface TransportConfig extends Config{
    String getScheme();

    String getHost();

    int getPort();
}
