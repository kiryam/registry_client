package ru.kiryam.registry_client;

import ru.kiryam.registry_client.exception.AuthenticationParseException;

import java.util.Map;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 *
 * Parses Docker-registry Www-Authenticate answer
 * Example:
 *   Www-Authenticate: Bearer realm="https://auth.docker.io/token",service="registry.docker.io"
 *   Www-Authenticate: Bearer realm="https://auth.docker.io/token",service="registry.docker.io",scope="repository:library/nginx:pull"
 *
 */
public interface AuthenticateParser {

    /**
     *
     * @param s like Bearer realm="https://auth.docker.io/token",service="registry.docker.io"
     * @return key-value with parameters realm, service and may be scope
     * @throws AuthenticationParseException
     */
    Map<String,String> parse(String s) throws AuthenticationParseException;
}
