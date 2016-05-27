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
            ArrayList<String> result = con.query(API_Connection.GETRESOURCES,params);
            for(String s:result){
                System.out.println(s);
            }
            while(!result.isEmpty()){
                Integer id = Integer.parseInt(result.get(0));
                String resource = result.get(1);
                Integer menge = Integer.parseInt(result.get(2));
                resources.add(new Resource(id,resource,menge));
                result.remove(0);
                result.remove(0);
                result.remove(0);
                System.out.println("Resource: " + id + ", " + resource + ", " + menge + "Stk. added!");
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
    public static void changeAmount(int id, int wert){
        Resource r = getRohstoff(id);
        changeAmount(r.getName(), wert);
    }

    public static Resource getRohstoff(String name){
        for(Resource s: resources){
            if(s.getName().equals(name)){
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
