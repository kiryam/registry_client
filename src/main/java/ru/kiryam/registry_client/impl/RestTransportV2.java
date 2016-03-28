package ru.kiryam.registry_client.impl;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;
import ru.kiryam.registry_client.*;
import ru.kiryam.registry_client.exception.ApiError;
import ru.kiryam.registry_client.exception.AuthenticationException;
import ru.kiryam.registry_client.exception.AuthenticationParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 *
 * Implementation of Docker Registry HTTP API V2
 * @see <a href=https://docs.docker.com/v1.7/registry/spec/api/">Docker Registry HTTP API V2</a>
 *
 *
 * TODO: validate api version by query GET /v2/
 * TODO: stack traces into logging
 */
public class RestTransportV2 implements Transport {
    private final TransportConfig config;
    private final HttpExecutor httpExecutor;
    private final JsonParser jsonParser;
    private final AuthenticateParser authenticateParser;
    private String token;

    public RestTransportV2(TransportConfig config, HttpExecutor httpExecutor, JsonParser jsonParser){
        this.config = config;
        this.httpExecutor = httpExecutor;
        this.jsonParser = jsonParser;
        this.authenticateParser = new AuthenticateParserImpl();
    }

    public URI getURI(String path) throws URISyntaxException {
        return new URI(config.getScheme(), "", config.getHost(), config.getPort(), path, null, null);
    }

    public URI getRealmPointUri(String realm, String service, String scope){
        if( scope != null){
            return URI.create(String.format("%s?service=%s&scope=%s", realm, service, scope));
        }
        return URI.create(String.format("%s?service=%s", realm, service));
    }

    public String getToken(URI forUri) throws AuthenticationException {
        ExecutorResponse executorResponse;
        try {
            executorResponse = httpExecutor.get(forUri);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AuthenticationException(String.format("Can not login [%s]", e.getMessage()));
        }

        try {
            if( !executorResponse.getHeaders().containsKey("Www-Authenticate") ){
                throw new AuthenticationException("No Www-Authenticate header");
            }

            Map<String, String> params;
            try {
                params = authenticateParser.parse(executorResponse.getHeaders().get("Www-Authenticate"));
            } catch (AuthenticationParseException e) {
                e.printStackTrace();
                throw new AuthenticationException(e.getMessage());
            }

            ExecutorResponse tokenQueryResponse;
            try {
                // TODO authenticate with login and password
                 tokenQueryResponse = httpExecutor.get(getRealmPointUri( params.get("realm"), params.get("service"), params.get("scope") ));
                System.nanoTime();
            } catch (IOException e) {
                e.printStackTrace();
                throw new AuthenticationException(e.getMessage());
            }

            JSONObject jsonObject = jsonParser.parse(tokenQueryResponse.getBody());
            return jsonObject.getString("token");

        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Cant parse json answer %s", e.getMessage()));
        }
    }

    private ExecutorResponse authorizedQuery(URI uri) throws IOException, AuthenticationException, ApiError {
        token = getToken(uri);

        Header[] headers = new Header[1];
        headers[0] = new BasicHeader("Authorization", String.format("Bearer %s", token));

        ExecutorResponse executorResponse = httpExecutor.get(uri, headers);

        if( executorResponse.getStatusCode() == 200 ){
           return executorResponse;
        }else if (executorResponse.getStatusCode() == 401){
            throw new AuthenticationException(String.format("Unauthorized %s", executorResponse.getBody()));
        }else{
            throw new ApiError(String.format("Unknown error [%s]", executorResponse.getBody()));
        }
    }

    @Override
    public DockerImageTags getImageTagsInfo(String name) throws AuthenticationException, ApiError {
        try {
            ExecutorResponse authorizedResponse = authorizedQuery(getURI(String.format("/v2/%s/tags/list", name)));
            JSONObject tagsResult;
            try {
                tagsResult = jsonParser.parse(authorizedResponse.getBody());
            } catch (JSONException e) {
                throw new ApiError(String.format("Failed to parse tags [%s]", e.getMessage()));
            }

            if( !tagsResult.has("tags") ){
                throw new ApiError("No tags field in answer");
            }

            ArrayList<String> tags = new ArrayList<>();
            for (int i=0; i<tagsResult.getJSONArray("tags").length(); i++) {
                tags.add( tagsResult.getJSONArray("tags").getString(i) );
            }

            return new BaseDockerImageTags(tags);

        } catch (IOException | URISyntaxException | JSONException e) {
            e.printStackTrace();
            throw new ApiError(e.getMessage());
        }
    }
}
