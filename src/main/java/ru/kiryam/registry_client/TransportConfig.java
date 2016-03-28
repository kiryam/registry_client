package ru.kiryam.registry_client;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 *
 * Docker registry transport config interface
 */
public interface TransportConfig extends Config{
    /**
     *
     * @return return scheme example: HTTPS
     */
    String getScheme();

    /**
     *
     * @return registry index host. Example: index.docker.io
     */
    String getHost();


    /**
     *
     * @return registry api port
     */
    int getPort();
}
