package com.example.darkayy.aueraaetas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.darkayy.aueraaetas.util.Playerdata;
import com.example.darkayy.aueraaetas.webapi.API_Connection;

/**
 * Created by leoka on 18.06.2016.
 */
public class PopupMarkt extends Activity {


    private Button button;
    private Button button2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_kaufen);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width *.9),(int)(height*.9));

        /**TODO
         * ALLES" KEVIN IST SCHEIßE
         * Id wird nicht übergeben
         */
        Intent i = getIntent();
        String id = i.getStringExtra("ID");
        System.out.println("SPID" + " ID " + id);

        final API_Connection con2 = new API_Connection();
        final String [] s = {API_Connection.APIKEY, id, "" + Playerdata.getId()};


        button = (Button)findViewById(R.id.btnJa);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con2.query(API_Connection.BUY, s);
                finish();
            }
        });
        button2 = (Button)findViewById(R.id.btnNein);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
