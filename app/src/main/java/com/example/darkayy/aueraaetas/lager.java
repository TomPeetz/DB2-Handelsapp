package com.example.darkayy.aueraaetas;

/**
 * Created by leoka on 22.05.2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Lager extends AppCompatActivity {


    ImageButton buttonP;
    ImageButton buttonL;
    ImageButton buttonG;
    ImageButton buttonM;
    Button btnPopup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lager);


        buttonP=(ImageButton)findViewById(R.id.btnProfile);
        buttonP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Profil.class);
                startActivity(i);
            }
        });

        buttonL=(ImageButton)findViewById(R.id.btnLager);
        buttonL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Lager.class);
                startActivity(i);
            }
        });

        buttonM=(ImageButton)findViewById(R.id.btnMarkt);
        buttonM.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Markt.class);
                startActivity(i);
            }
        });

        buttonG=(ImageButton)findViewById(R.id.btnGebaude);
        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Gebaude.class);
                startActivity(i);
            }
        });

        btnPopup=(Button)findViewById(R.id.btnLagerErweitern);
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Pop.class);
                startActivity(i);
            }
        });
    }
}
