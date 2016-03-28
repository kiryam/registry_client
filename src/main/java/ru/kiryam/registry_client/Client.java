package ru.kiryam.registry_client;

import ru.kiryam.registry_client.exception.ApiError;
import ru.kiryam.registry_client.exception.AuthenticationException;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 *
 * Base interface of library
 */
public interface Client {
    /**
     *
     * @param name docker image name (like: "library/nginx")
     * @return returns info about images like tags.
     * @throws AuthenticationException
     * @throws ApiError
     */
    DockerImageInfo getImageInfo(String name) throws AuthenticationException, ApiError;
}
