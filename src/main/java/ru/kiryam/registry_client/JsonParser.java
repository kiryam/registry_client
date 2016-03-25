package ru.kiryam.registry_client;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kiryam on 25/03/16.
 */
public interface JsonParser {
    JSONObject parse(String data) throws JSONException;
}
