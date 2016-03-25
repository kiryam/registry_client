package ru.kiryam.registry_client.impl;

import ru.kiryam.registry_client.DockerImageTags;

import java.util.List;

/**
 * Created by kiryam on 25/03/16.
 */
public class BaseDockerImageTags implements DockerImageTags {
    private final List<String> tags;

    public BaseDockerImageTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public List getTags() {
        return tags;
    }
}
