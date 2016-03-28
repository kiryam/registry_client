package ru.kiryam.registry_client;

import java.util.List;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 *
 * Docker tags acess interafce
 */
public interface DockerImageTags {

    /**
     *
     * @return list of available tags
     */
    List<String> getTags();
}
