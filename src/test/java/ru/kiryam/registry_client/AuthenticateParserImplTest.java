package ru.kiryam.registry_client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.kiryam.registry_client.impl.AuthenticateParserImpl;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by kiryam on 25/03/16.
 */
public class AuthenticateParserImplTest {
    @Test
    public void testParse() throws Exception {
        AuthenticateParser authenticateParser = new AuthenticateParserImpl();

        Map<String, String> params = authenticateParser.parse("Bearer realm=\"https://auth.docker.io/token\",service=\"registry.docker.io\"");

        assertEquals("https://auth.docker.io/token", params.get("realm"));
        assertEquals("registry.docker.io", params.get("service"));
        assertEquals(2, params.size());

        Map<String, String> params2 = authenticateParser.parse("Bearer realm=\"https://auth.docker.io/token\",service=\"registry.docker.io\",scope=\"repository:library/nginx:pull\"");

        assertEquals("https://auth.docker.io/token", params2.get("realm"));
        assertEquals("registry.docker.io", params2.get("service"));
        assertEquals("repository:library/nginx:pull", params2.get("scope"));
        assertEquals(3, params2.size());


    }
}