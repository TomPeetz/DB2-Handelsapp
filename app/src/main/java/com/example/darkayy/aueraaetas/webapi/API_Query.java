package com.example.darkayy.aueraaetas.webapi;

import com.example.darkayy.aueraaetas.util.JsonReader;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * Created by Tobias Theus on 18/05/2016.
 */
public class API_Query implements Callable<ArrayList<String>>{
    private static final String BASEURL 	= "https://ttheus.nunki.uberspace.de/webapi/src/public/";
    private String queryURL 				= BASEURL;

    public void prepareStatement(String ext){
        this.queryURL = BASEURL + ext;
        System.out.println(queryURL);
    }

    @Override
    public ArrayList<String> call() throws Exception {
        URL url = new URL(queryURL);
        URLConnection connection = url.openConnection();
        connection.connect();
        InputStream is = connection.getInputStream();
        String json = new Scanner( is ).useDelimiter( "\\Z" ).next();
        is.close();
        JsonReader reader = new JsonReader();
        return reader.parseJson(json);
    }

}
