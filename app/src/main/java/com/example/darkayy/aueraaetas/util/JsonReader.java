package com.example.darkayy.aueraaetas.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Tobias Theus on 13/05/2016.
 */
public class JsonReader {

    public ArrayList<String> parseJson(String json){
        try {
            ArrayList<String> result = new ArrayList<String>();
            JSONObject jObject = new JSONObject(json);
            jObject.keys();
            Iterator<String> test = jObject.keys();
            while(test.hasNext()){
                result.add(jObject.getString(test.next()));
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
