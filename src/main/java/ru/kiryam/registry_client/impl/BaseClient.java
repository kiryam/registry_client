package ru.kiryam.registry_client.impl;

import ru.kiryam.registry_client.Client;
import ru.kiryam.registry_client.DockerImageInfo;
import ru.kiryam.registry_client.DockerImageTags;
import ru.kiryam.registry_client.Transport;
import ru.kiryam.registry_client.exception.ApiError;
import ru.kiryam.registry_client.exception.AuthenticationException;

import java.net.URI;

/**
 * Created by kiryam on 25/03/16.
 * TODO access to private repo (provide user and password)
 */
public class BaseClient implements Client {
    private final Transport transport;

    public BaseClient(Transport transport){
        this.transport = transport;
    }

    public BaseClient(){
        this.transport = new RestTransportV2(new BaseTransportConfig(URI.create("https://index.docker.io")),
                new ApacheHttpExecutor(), new SimpleJsonParser());
    }

    public DockerImageInfo getImageInfo(String name) throws AuthenticationException, ApiError {
        DockerImageTags tags = transport.getImageTagsInfo(name);

        return new BaseDockerImageInfo(tags);
    }
}
