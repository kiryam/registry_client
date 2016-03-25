package ru.kiryam.registry_client;

import ru.kiryam.registry_client.exception.ApiError;
import ru.kiryam.registry_client.exception.AuthenticationException;

/**
 * Created by kiryam on 25/03/16.
 */
public interface Transport {
    DockerImageTags getImageTagsInfo(String name) throws AuthenticationException, ApiError;
}
