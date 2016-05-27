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
            System.out.print("JSONREADER: Trying to read String - " + json);
            // Unterscheidung zwischen JSON Arrays ( [{"Rohstoffname":"Golderz","Menge":"20"},{"Rohstoffname":"Kohle","Menge":"5"}] )
            if(json.startsWith("[")){
                System.out.print("JSONREADER: Detected JSON-Array...");
                JSONArray jArray = new JSONArray(json);
                for(int i = 0; i < jArray.length(); i++){
                    JSONObject jObject = jArray.getJSONObject(i);
                    Iterator<String> test = jObject.keys();
                    while(test.hasNext()){
                        result.add(jObject.getString(test.next()));
                    }
                }
            // und JSON Objects {"Rohstoffname":"Golderz","Menge":"20"}
            }else {
                System.out.print("JSONREADER: Detected JSON-Object...");
                JSONObject jObject = new JSONObject(json);
                Iterator<String> test = jObject.keys();
                while(test.hasNext()){
                    result.add(jObject.getString(test.next()));
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
