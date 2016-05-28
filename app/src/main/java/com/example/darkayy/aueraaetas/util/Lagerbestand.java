package com.example.darkayy.aueraaetas.util;

import com.example.darkayy.aueraaetas.webapi.API_Connection;
import com.example.darkayy.aueraaetas.webapi.API_Exception;

import java.util.ArrayList;

/**
 * Created by Tobias Theus 27/05/2016.
 */
public class Lagerbestand {
    private static ArrayList<Resource> resources = new ArrayList<Resource>();

    public static void getLagerbestand(){
        try {
            API_Connection con = new API_Connection();
            ArrayList<String> params = new ArrayList<String>();
            params.add(API_Connection.APIKEY);
            params.add(""+Playerdata.getId());
            JsonResult result = con.query(API_Connection.GETRESOURCES,params);
            String[] exp = {"id","name","menge"};
            resources.clear();
            while(!result.isEmpty()){
                ArrayList<String> r = result.parseResult(exp);
                int id = Integer.parseInt(r.get(0));
                String name = r.get(1);
                int menge = Integer.parseInt(r.get(2));
                Resource res = new Resource(id,name,menge);
                resources.add(res);
            }
        } catch (API_Exception e) {
            e.printStackTrace();
        }
    }
    public static void changeAmount(String name, int wert){
        Resource r = getRohstoff(name);
        r.setMenge(r.getMenge() + wert);
        //ToDo: Lagerbestand in der Datenbank anpassen


    }
    public static ArrayList<Integer> getMengen(){
        ArrayList<Integer> mengen = new ArrayList<Integer>();
        for(Resource r : resources){
            mengen.add(r.getMenge());
        }
        return mengen;
    }

    public static void changeAmount(int id, int wert){
        Resource r = getRohstoff(id);
        changeAmount(r.getName(), wert);
    }

    public static Resource getRohstoff(String name){
        for(Resource s: resources){
            if(s.getName().contains(name)){
                return s;
            }
        }
        return null;
    }

    public static Resource getRohstoff(int id){
        for(Resource s: resources){
            if(s.getId()==id){
                return s;
            }
        }
        return null;
    }
}
