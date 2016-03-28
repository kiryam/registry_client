package ru.kiryam.registry_client;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 *
 * Docker image info interface
 */
public interface DockerImageInfo {

    /**
     * @return Tags access interface
     */
    DockerImageTags getTagInfo();
}
