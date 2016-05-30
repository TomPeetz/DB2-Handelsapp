package com.example.darkayy.aueraaetas.util;

import com.example.darkayy.aueraaetas.webapi.API_Connection;
import com.example.darkayy.aueraaetas.webapi.API_Exception;

import java.util.ArrayList;

/**
 * Created by Tom on 29.05.2016.
 */
public class Gebaeudeverwaltung {
    private static ArrayList<Gebaeude> gebaeude = new ArrayList<Gebaeude>();
    private static ArrayList<Besitz> besitz = new ArrayList<Besitz>();

    public static ArrayList<Gebaeude> getGebaeudeList() {
        return gebaeude;
    }

    public static void getGebaeude() {
        API_Connection con = new API_Connection();
        JsonResult result = con.query(API_Connection.GETGEBAEUDE, new String[]{API_Connection.APIKEY});
        String[] exp = {"Gebaeude_id", "Name", "Beschreibung", "Typ", "Baudauer", "Produktionsdauer", "gelagert"};
        gebaeude.clear();
        while(!result.isEmpty()) {
            ArrayList<String> r = result.parseResult(exp);
            Gebaeude geb = new Gebaeude(Integer.parseInt(r.get(0)),
                                        r.get(1),
                                        r.get(2),
                                        r.get(3),
                                        Integer.parseInt(r.get(4)),
                                        Integer.parseInt(r.get(5)),
                                        Integer.parseInt(r.get(6)));
            gebaeude.add(geb);
        }
        for(Gebaeude g:gebaeude){
            System.out.println("GEBAEUDEVERWALTUNG: " + g.getName());
        }
    }

    public static void getBesitz(){
        API_Connection con = new API_Connection();
        String id =  ""+Playerdata.getId();
        String[] params = {API_Connection.APIKEY, id};
        JsonResult res = con.query(API_Connection.GETBESITZ, params);
        String[] exp = {"id", "level"};
        besitz.clear();
        while(!res.isEmpty()) {
            ArrayList<String> r = res.parseResult(exp);
            Besitz b = new Besitz(Integer.parseInt(r.get(0)), Integer.parseInt(r.get(1)));
            besitz.add(b);
        }
    }

    public static void produziereRohstoffe(int time){
        for(Besitz b:besitz){
            Gebaeude g = findBuilding(b.getId());
            if(g != null){
                if(time == 0){
                    if(g.getProduktionsdauer() == 900){
                        int rohstoffid = g.rohstoff_id_prodziert_gelagert;
                        Lagerbestand.changeAmount(rohstoffid, 1);
                    }
                } else {
                    int rohstoffid = g.rohstoff_id_prodziert_gelagert;
                    Lagerbestand.changeAmount(rohstoffid, 1);
                }
            }
        }
    }

    private static Gebaeude findBuilding(int id){
        for(Gebaeude g : gebaeude){
            if(g.getId() == id){
                return g;
            }
        }
        return null;
    }

    public static void geb4PlayerAnlegen() {
        Gebaeudeverwaltung.getGebaeude();
        API_Connection con = new API_Connection();
        for (Gebaeude geb: gebaeude) {
            con.query(API_Connection.NEWGEBAEUDE, new String[]{API_Connection.APIKEY, Integer.toString(Playerdata.getId()),Integer.toString(geb.getId())});
        }
    }
}
