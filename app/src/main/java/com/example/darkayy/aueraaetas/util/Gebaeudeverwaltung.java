package com.example.darkayy.aueraaetas.util;

import com.example.darkayy.aueraaetas.webapi.API_Connection;
import com.example.darkayy.aueraaetas.webapi.API_Exception;

import java.util.ArrayList;

/**
 * Created by Tom on 29.05.2016.
 */
public class Gebaeudeverwaltung {
    private static ArrayList<Gebaeude> gebaeude = new ArrayList<Gebaeude>();

    public static void getGebaeude() {
        API_Connection con = new API_Connection();
        JsonResult result = con.query(API_Connection.GETGEBAEUDE, new String[]{API_Connection.APIKEY});
        String[] exp = {"ID", "Name", "Beschreibung", "Typ", "Baudauer", "Produktionsdauer", "RohstoffID"};
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
    }

    public static void geb4PlayerAnlegen(int playerID) {
        API_Connection con = new API_Connection();
        for (Gebaeude geb: gebaeude) {
            con.query(API_Connection.NEWGEBAEUDE, new String[]{API_Connection.APIKEY, Integer.toString(playerID),Integer.toString(geb.getId())});
        }
    }
}
