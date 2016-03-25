package ru.kiryam.registry_client.impl;

import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import ru.kiryam.registry_client.ExecutorResponse;
import ru.kiryam.registry_client.HttpExecutor;

/**
 * Created by kiryam on 25/03/16.
 */
public class ApacheHttpExecutor implements HttpExecutor {
    @Override
    public ExecutorResponse get(URI uri) throws IOException {
        return get(uri, new Header[0]);
    }

    @Override
    public ExecutorResponse get(URI uri, Header[] headers) throws IOException {
        SSLContextBuilder builder = new SSLContextBuilder();
        SSLConnectionSocketFactory sslsf = null;
        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            sslsf = new SSLConnectionSocketFactory( builder.build());
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();


        HttpGet httpGet = new HttpGet(uri);
        if (headers != null) {
            httpGet.setHeaders(headers);
        }

        CloseableHttpResponse response = httpclient.execute(httpGet);

        BasicExecutorResponse executorResponse = new BasicExecutorResponse();
        executorResponse.setBody(IOUtils.toString(response.getEntity().getContent()));
        executorResponse.setHeaders(response.getAllHeaders());
        executorResponse.setStatusCode(response.getStatusLine().getStatusCode());

        response.close();

        return executorResponse;
    }
}