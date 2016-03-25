package ru.kiryam.registry_client;

import java.util.Map;

/**
 * Created by kiryam on 25/03/16.
 */
public interface ExecutorResponse {
    String getBody();
    Map<String, String> getHeaders();

    int getStatusCode();
}
