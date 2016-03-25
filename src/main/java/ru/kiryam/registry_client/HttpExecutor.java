package ru.kiryam.registry_client;

import org.apache.http.Header;

import java.io.IOException;
import java.net.URI;

/**
 * Created by kiryam on 25/03/16.
 */
public interface HttpExecutor {
    ExecutorResponse get(URI uri) throws IOException;
    ExecutorResponse get(URI uri, Header[] headers) throws IOException;
}
