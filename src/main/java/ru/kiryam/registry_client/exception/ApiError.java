package ru.kiryam.registry_client.exception;


/**
 * Created by kiryam on 25/03/16.
 */
public class ApiError extends Exception {
    public ApiError(String e) {
        super(e);
    }
}
