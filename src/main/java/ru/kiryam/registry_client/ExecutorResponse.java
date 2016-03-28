package ru.kiryam.registry_client;

import java.util.Map;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 *
 * Response of http executor.
 */
public interface ExecutorResponse {

    /**
     *
     * @return plain text body
     */
    String getBody();

    /**
     * 
     * @return headers key-value
     */
    Map<String, String> getHeaders();


    /**
     *
     * @return http code of answer
     */
    int getStatusCode();
}
