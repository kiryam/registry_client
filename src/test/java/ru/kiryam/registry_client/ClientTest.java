package ru.kiryam.registry_client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.kiryam.registry_client.exception.ApiError;
import ru.kiryam.registry_client.exception.AuthenticationException;
import ru.kiryam.registry_client.impl.BaseDockerImageTags;

import java.util.ArrayList;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * Created by kiryam on 25/03/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientTest {
    @Mock
    private Transport transport;

    @Test
    public void test_getImageInfo() throws AuthenticationException, ApiError {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        tags.add("tag3");
        when(transport.getImageTagsInfo(anyString())).thenReturn(new BaseDockerImageTags(tags));

        Client client = new Client(transport);

        DockerImageInfo imageInfo = client.getImageInfo(anyString());
        assertNotNull(imageInfo);
        assertEquals(3, imageInfo.getTagInfo().getTags().size());
        assertEquals("tag2", imageInfo.getTagInfo().getTags().get(1));

        verify(transport).getImageTagsInfo(anyString());
    }
}