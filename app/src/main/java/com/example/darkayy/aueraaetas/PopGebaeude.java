package com.example.darkayy.aueraaetas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by leoka on 22.05.2016.
 */
public class PopGebaeude extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_gebaeude);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .8));

        Intent i = getIntent();
        String gebaeudename = i.getStringExtra("Gebäudename");
        String gebaeudeinfo = i.getStringExtra("Gebaeudeinfos");


        TextView name = (TextView)findViewById(R.id.txtGebaeudename);
        name.setText(gebaeudename);
        TextView beschr = (TextView)findViewById(R.id.txtBeschreibung);
        beschr.setText(gebaeudeinfo);

    }
}