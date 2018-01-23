package br.com.accera.starwarscatalog.network.services;

import org.json.JSONObject;

/**
 * Created by juliano.nardon on 22/01/18.
 */

public interface OnTaskCompleted{
    void onTaskCompleted(JSONObject result);
}
