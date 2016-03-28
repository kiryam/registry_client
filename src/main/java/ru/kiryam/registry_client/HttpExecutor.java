package ru.kiryam.registry_client;

import org.apache.http.Header;

import java.io.IOException;
import java.net.URI;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 */
public interface HttpExecutor {
    /**
     * Do HTTP GET request to uri
     *
     * @param uri full uri
     * @return ExecutorResponse object
     * @throws IOException
     */
    ExecutorResponse get(URI uri) throws IOException;

    /**
     * Do HTTP GET request to uri with headers
     *
     * @param uri full uri
     * @param headers Apache headers
     * @return Executor response object
     * @throws IOException
     */
    ExecutorResponse get(URI uri, Header[] headers) throws IOException;
}
