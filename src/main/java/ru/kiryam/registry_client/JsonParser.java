package ru.kiryam.registry_client;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Ivannikov Kirill (kiryam@kiryam.ru)
 *
 * JSON parser interface
 */

public interface JsonParser {
    /**
     * Parses given plain-text json to SONObject<
     * @param data plain-text json
     * @return JSONObject
     * @throws JSONException
     */
    JSONObject parse(String data) throws JSONException;
}
