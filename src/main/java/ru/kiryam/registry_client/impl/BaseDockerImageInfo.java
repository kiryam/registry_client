package ru.kiryam.registry_client.impl;

import ru.kiryam.registry_client.DockerImageInfo;
import ru.kiryam.registry_client.DockerImageTags;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 */
public class BaseDockerImageInfo implements DockerImageInfo {
    private final DockerImageTags tags;

    public BaseDockerImageInfo(DockerImageTags tags) {
        this.tags = tags;
    }

    @Override
    public DockerImageTags getTagInfo() {
        return tags;
    }
}
