package com.example.darkayy.aueraaetas.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Tobias Theus on 13/05/2016.
 */
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {
    public JsonResult parseJson(String json){
        try {
            JsonResult result = new JsonResult();
            System.out.println("JSONREADER: Trying to read String - " + json);
            // Unterscheidung zwischen JSON Arrays ( [{"Rohstoffname":"Golderz","Menge":"20"},{"Rohstoffname":"Kohle","Menge":"5"}] )
            if(json.startsWith("[")){
                System.out.println("JSONREADER: Detected JSON-Array...");
                JSONArray jArray = new JSONArray(json);
                for(int i = 0; i < jArray.length(); i++){
                    JSONObject jObject = jArray.getJSONObject(i);
                    Iterator<String> test = jObject.keys();
                    while(test.hasNext()){
                        String spalte = test.next();
                        result.add(spalte.toLowerCase(), jObject.optString(spalte));
                    }
                }
                // und JSON Objects {"Rohstoffname":"Golderz","Menge":"20"}
            }else {
                System.out.println("JSONREADER: Detected JSON-Object...");
                JSONObject jObject = new JSONObject(json);
                Iterator<String> test = jObject.keys();
                while(test.hasNext()){
                    String spalte = test.next();
                    result.add(spalte.toLowerCase(), jObject.optString(spalte));
                }

            }
            return result;
        } catch (JSONException e) {
            System.out.print("JSONREADER: Error reading String! ");
            e.printStackTrace();
        }
        return null;
    }
}
