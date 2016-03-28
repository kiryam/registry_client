package ru.kiryam.registry_client.impl;

import org.json.JSONException;
import org.json.JSONObject;
import ru.kiryam.registry_client.JsonParser;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 */
public class SimpleJsonParser implements JsonParser {
    @Override
    public JSONObject parse(String data) throws JSONException {
        return new JSONObject(data);
    }
}
