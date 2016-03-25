package ru.kiryam.registry_client.impl;

import ru.kiryam.registry_client.DockerImageInfo;
import ru.kiryam.registry_client.DockerImageTags;

/**
 * Created by kiryam on 25/03/16.
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
