package ru.kiryam.registry_client.impl;

import ru.kiryam.registry_client.ExecutorResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 */
public class AbstractExecutorResponse implements ExecutorResponse {
    protected String body;
    protected Map<String, String> headers;
    protected int statusCode;

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public Map<String, String> getHeaders() {
        if (headers != null){
            return headers;
        }
        return new HashMap<>();
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }


}
