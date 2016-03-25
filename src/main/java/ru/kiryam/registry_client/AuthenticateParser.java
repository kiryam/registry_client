package ru.kiryam.registry_client;

import ru.kiryam.registry_client.exception.AuthenticationParseException;

import java.util.Map;

/**
 * Created by kiryam on 25/03/16.
 */
public interface AuthenticateParser {
    Map<String,String> parse(String s) throws AuthenticationParseException;
}
