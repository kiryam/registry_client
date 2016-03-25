package ru.kiryam.registry_client.impl;

import ru.kiryam.registry_client.AuthenticateParser;
import ru.kiryam.registry_client.exception.AuthenticationParseException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiryam on 25/03/16.
 */
public class AuthenticateParserImpl implements AuthenticateParser {
    /*
        Example:
        Bearer realm="https://auth.docker.io/token",service="registry.docker.io"
        Bearer realm="https://auth.docker.io/token",service="registry.docker.io",scope="repository:library/nginx:pull"
     */
    @Override
    public Map<String, String> parse(String wwwAuth) throws AuthenticationParseException {
        HashMap<String, String> params = new HashMap<>();

        if( wwwAuth.split(" ").length > 0 && wwwAuth.split(" ")[0].equals("Bearer") ){
            try {
                for (String param : wwwAuth.substring(7).split(",")) {
                    String key = param.split("=")[0];
                    String value = param.split("=")[1];
                    value = value.substring(1, value.length()-1); // remove quotes
                    params.put(key, value);
                }
            }catch (NullPointerException e) {
                throw new AuthenticationParseException("Unknown Www-Authenticate format");
            }

            if( !params.containsKey("realm") ){
                throw new AuthenticationParseException("realm param not found");
            }

        }else{
            throw new AuthenticationParseException("Unknown Www-Authenticate format");
        }

        return params;
    }
}
