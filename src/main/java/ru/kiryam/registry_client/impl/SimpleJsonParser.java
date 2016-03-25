package ru.kiryam.registry_client.impl;

import org.json.JSONException;
import org.json.JSONObject;
import ru.kiryam.registry_client.JsonParser;

/**
 * Created by kiryam on 25/03/16.
 */
public class SimpleJsonParser implements JsonParser {
    @Override
    public JSONObject parse(String data) throws JSONException {
        return new JSONObject(data);
    }
}
