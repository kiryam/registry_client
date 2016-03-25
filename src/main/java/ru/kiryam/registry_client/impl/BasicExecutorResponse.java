package ru.kiryam.registry_client.impl;

import org.apache.http.Header;
import java.util.HashMap;

/**
 * Created by kiryam on 25/03/16.
 */
public class BasicExecutorResponse extends AbstractExecutorResponse {
    public void setBody(String body) {
        this.body = body;
    }

    public void setHeaders(Header[] headers) {
        this.headers = new HashMap<>();
        for(int i=0; i<headers.length; i++){
            this.headers.put(headers[i].getName(), headers[i].getValue());
        }
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
