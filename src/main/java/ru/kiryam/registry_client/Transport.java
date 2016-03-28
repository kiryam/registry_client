package ru.kiryam.registry_client;

import ru.kiryam.registry_client.exception.ApiError;
import ru.kiryam.registry_client.exception.AuthenticationException;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 */
public interface Transport {
    /**
     * Transport for access Docker registry api
     * @param name docker image name, if you use "base images" like "nginx", you must specify repository "library"
     *             example: "library/nginx"
     *
     * @return Docker tags info object
     * @throws AuthenticationException
     * @throws ApiError
     */
    DockerImageTags getImageTagsInfo(String name) throws AuthenticationException, ApiError;
}
