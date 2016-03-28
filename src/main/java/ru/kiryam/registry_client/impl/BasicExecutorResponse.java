package ru.kiryam.registry_client.impl;

import org.apache.http.Header;
import java.util.HashMap;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 */
public class BasicExecutorResponse extends AbstractExecutorResponse {
    public void setBody(String body) {
        this.body = body;
    }

    public void setHeaders(Header[] headers) {
        this.headers = new HashMap<>();
        for (Header header : headers) {
            this.headers.put(header.getName(), header.getValue());
        }
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
