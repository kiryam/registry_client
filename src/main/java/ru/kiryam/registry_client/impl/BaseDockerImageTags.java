package ru.kiryam.registry_client.impl;

import ru.kiryam.registry_client.DockerImageTags;

import java.util.List;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 */
public class BaseDockerImageTags implements DockerImageTags {
    private final List<String> tags;

    public BaseDockerImageTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public List<String> getTags() {
        return tags;
    }
}
