package ru.kiryam.registry_client;

import org.apache.http.*;
import org.apache.http.message.BasicHeader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.kiryam.registry_client.impl.*;

import java.net.URI;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 */
@RunWith(MockitoJUnitRunner.class)
public class RestTransportV2Test {
    @Mock
    private HttpExecutor httpExecutor;

    @Mock
    private TransportConfig transportConfig;

    @Test
    public void testGetImageInfo() throws Exception {
        RestTransportV2 transport = spy(new RestTransportV2(transportConfig, httpExecutor, new SimpleJsonParser()));

        URI v2Uri = URI.create("https://registry.docker.io/v2/library/test/tags/list");
        doReturn(v2Uri).when(transport).getURI("/v2/library/test/tags/list");

        BasicExecutorResponse unauthorizedExecutorResponse = new BasicExecutorResponse();
        unauthorizedExecutorResponse.setBody("{\"errors\":[{\"code\":\"UNAUTHORIZED\",\"message\":\"access to the requested resource is not authorized\",\"detail\":null}]}");
        unauthorizedExecutorResponse.setStatusCode(401);

        Header[] headers = new Header[6];
        headers[0] = new BasicHeader("Content-Type", "application/json; charset=utf-8");
        headers[1] = new BasicHeader("Docker-Distribution-Api-Version", "registry/2.0");
        headers[2] = new BasicHeader("Www-Authenticate", "Bearer realm=\"https://auth.docker.io/token\",service=\"registry.docker.io\"");
        headers[3] = new BasicHeader("Date", "Fri, 25 Mar 2016 09:21:18 GMT");
        headers[4] = new BasicHeader("Content-Length", "114");
        headers[5] = new BasicHeader("Strict-Transport-Security", "max-age=31536000");
        unauthorizedExecutorResponse.setHeaders(headers);

        when(httpExecutor.get(v2Uri)).thenReturn(unauthorizedExecutorResponse);

        BasicExecutorResponse authorizedExecutorResponse = new BasicExecutorResponse();
        authorizedExecutorResponse.setBody("{\"tags\": [\"tag1\", \"tag2\"]}");
        authorizedExecutorResponse.setStatusCode(200);

        when(httpExecutor.get(any(URI.class), any(Header[].class))).thenReturn(authorizedExecutorResponse);

        BasicExecutorResponse tokenExecutorResponse = new BasicExecutorResponse();
        tokenExecutorResponse.setBody("{\"token\":\"123\"}");
        tokenExecutorResponse.setStatusCode(200);

        Header[] headers2 = new Header[4];
        headers2[0] = new BasicHeader("Content-Type", "application/json; charset=utf-8");
        headers2[1] = new BasicHeader("Date", "Fri, 25 Mar 2016 09:21:18 GMT");
        headers2[2] = new BasicHeader("Content-Length", "114");
        headers2[3] = new BasicHeader("Strict-Transport-Security", "max-age=31536000");
        tokenExecutorResponse.setHeaders(headers2);

        URI tokenURI = URI.create("https://auth.docker.io?service=registry.docker.io&scope=library/test:pull");

        doReturn(tokenURI).when(transport).getRealmPointUri(anyString(), anyString(), anyString());
        when(httpExecutor.get(tokenURI)).thenReturn(tokenExecutorResponse);

        DockerImageTags imageTags = transport.getImageTagsInfo("library/test");

        assertEquals(2, imageTags.getTags().size());
        assertEquals("tag1", imageTags.getTags().get(0));

        verify(httpExecutor).get(v2Uri);
        verify(httpExecutor).get(tokenURI);
        verify(httpExecutor).get(any(URI.class), any(Header[].class));
    }
}