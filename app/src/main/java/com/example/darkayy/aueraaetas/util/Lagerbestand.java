package com.example.darkayy.aueraaetas.util;

import com.example.darkayy.aueraaetas.webapi.API_Connection;
import com.example.darkayy.aueraaetas.webapi.API_Exception;

import java.util.ArrayList;

/**
 * Created by Tobias Theus 27/05/2016.
 */
public class Lagerbestand {
    private static ArrayList<Resource> resources = new ArrayList<Resource>();

    /**
     * Gleicht den Lagerbestand mit der Datenbank ab.
     */
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
            updateTime();
        } catch (API_Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTime(){
        API_Connection con = new API_Connection();
        ArrayList<String> params = new ArrayList<String>();
        params.add(API_Connection.APIKEY);
        params.add(""+Playerdata.getId());
        try {
            JsonResult result = con.query(API_Connection.GETMARKETTIME,params);
            String[] exp = {"lagerupdate"};
            ArrayList<String> r = result.parseResult(exp);
            return r.get(0);
        } catch (API_Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void updateTime(){
        API_Connection con = new API_Connection();
        ArrayList<String> params = new ArrayList<String>();
        params.add(API_Connection.APIKEY);
        params.add(""+Playerdata.getId());
        try {
            JsonResult result = con.query(API_Connection.SETMARKETTIME,params);
            String[] exp = {"lagerupdate"};
            ArrayList<String> r = result.parseResult(exp);
        } catch (API_Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ändert die Anzahl eines Rohstoffes in der Datenbank und im Programm.
     * @param name Name des Rohstoffes.
     * @param wert Wert um den die Menge geändert werden soll.
     */
    public static void changeAmount(String name, int wert){
        Resource r = getRohstoff(name);
        r.setMenge(r.getMenge() + wert);
        //ToDo: Lagerbestand in der Datenbank anpassen
        API_Connection con  = new API_Connection();
        ArrayList<String> params = new ArrayList<String>();
        params.add(API_Connection.APIKEY);
        params.add(""+Playerdata.getId());
        params.add(""+r.getId());
        params.add(""+wert);
        try {
            JsonResult result = con.query(API_Connection.CHANGEAMOUNT, params);
            String[] exp = {"menge"};
            if(!result.isEmpty()){
                System.out.println("LAGERBESTAND: Erfolgreich geändert.");
                System.out.println("LAGERBESTAND: " + r.getName() + " neuer Stand: " + result.parseResult(exp).get(0));
            }
        } catch (API_Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Funktion die die Mengen der Rohstoffe wiedergibt.
     * @return Liefert ein Array in dem nur die Mengen der einzelnen Rohstoffe drin stehen.
     */
    public static ArrayList<Integer> getMengen(){
        ArrayList<Integer> mengen = new ArrayList<Integer>();
        for(Resource r : resources){
            mengen.add(r.getMenge());
        }
        return mengen;
    }
    /**
     * Ändert die Anzahl eines Rohstoffes in der Datenbank und im Programm.
     * @param id ID des Rohstoffes.
     * @param wert Wert um den die Menge geändert werden soll.
     */
    public static void changeAmount(int id, int wert){
        Resource r = getRohstoff(id);
        changeAmount(r.getName(), wert);
    }

    /**
     * Funktion die einen Rohstoff wiedergibt.
     * @param name Name des Rohstoffs.
     * @return Rohstoff
     */
    public static Resource getRohstoff(String name){
        for(Resource s: resources){
            if(s.getName().equals(name)){
                System.out.println("LAGERBESTAND: " + s.getName() + " gefunden!");
                return s;
            }
        }
        return null;
    }
    /**
     * Funktion die einen Rohstoff wiedergibt.
     * @param id ID des Rohstoffs.
     * @return Rohstoff
     */
    public static Resource getRohstoff(int id){
        for(Resource s: resources){
            if(s.getId()==id){
                return s;
            }
        }
        return null;
    }

    public static int getNahrung(){
        int amount = 0;
        Resource fisch = getRohstoff("Fisch");
        Resource fleisch = getRohstoff("Fleisch");
        Resource brot = getRohstoff("Brot");
        amount = (fleisch.getMenge() * 1) + (fisch.getMenge() * 2) + (brot.getMenge() * 3);
        System.out.println("LAGERBESTAND: Menge Nahrung errechnet... Menge: " + amount );
        return amount;
    }
}
