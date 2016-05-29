package com.example.darkayy.aueraaetas;


import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import com.example.darkayy.aueraaetas.util.Gebaeudeverwaltung;
import com.example.darkayy.aueraaetas.util.Lagerbestand;
import com.example.darkayy.aueraaetas.util.Playerdata;
import com.example.darkayy.aueraaetas.webapi.API_Connection;

import java.util.TimerTask;

/**
 * Created by Tobias Theus on 28.05.2016.
 * SO macht man das Tom ;D
 */
public class UpdateLagerService extends IntentService {

    public UpdateLagerService(){
        super("LagerUpdateService");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        API_Connection con = new API_Connection();
        int count = 0;
        while(true){
            Lagerbestand.getLagerbestand();
            System.out.println("UPDATESERVICE: Lagerbestand geupdatet! Bis in 5 Minuten sheesh!" );
            SystemClock.sleep(900000);
            Gebaeudeverwaltung.getBesitz();
            Gebaeudeverwaltung.produziereRohstoffe(count++);
            count = count % 2;
        }
    }
}
