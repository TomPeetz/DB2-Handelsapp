package com.example.darkayy.aueraaetas;


import com.example.darkayy.aueraaetas.util.Playerdata;
import com.example.darkayy.aueraaetas.webapi.API_Connection;

/**
 * Created by Tom on 28.05.2016.
 */
public class UpdateLager implements Runnable {
    @Override
    public void run() {
        API_Connection con = new API_Connection();
        while (true) {
            con.query(API_Connection.UPDATEROHSTOFFEFROMTIME,new String[]{Integer.toString(Playerdata.getId())});
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
