package com.example.darkayy.aueraaetas;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

public class Markt extends AppCompatActivity {

    ImageButton buttonP;
    ImageButton buttonL;
    ImageButton buttonG;
    ImageButton buttonM;

    ArrayList<Button> lokaleHaendlerButtons = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markt);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();


        TabHost.TabSpec tabSpec = tabHost.newTabSpec("lokaleHaendler");
        tabSpec.setContent(R.id.lokaleHaendler);
        tabSpec.setIndicator("Lokale Händler");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("angeboteErstellen");
        tabSpec.setContent(R.id.angeboteErstellen);
        tabSpec.setIndicator("Angebote erstellen");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("angeboteSuchen");
        tabSpec.setContent(R.id.angeboteSuchen);
        tabSpec.setIndicator("Angebote suchen");
        tabHost.addTab(tabSpec);

        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#000000"));
        }


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

    }

    public void fillLokaleHaendler(ArrayList<String> arrayList){

        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnGolderzEinkauf));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnGolderzVerkauf));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnKohleEinkauf));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnKohleVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnEisenerzEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnEisenerzVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnBaumstammEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnBaumstammVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnSteinEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnSteinVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnLehmEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnLehmVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnGetreideEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnGetreideVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnFischEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnFischVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnFleischEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnFleischVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnWolleEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnWolleVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnGoldbarrenEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnGoldbarrenVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnEisenbarrenEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnEisenbarrenVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnBrettEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnBrettVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnZiegelsteinEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnZiegelsteinVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnMehlEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnMehlVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnLeinenEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnLeinenVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnWaffenEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnWaffenVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnWerkzeugeEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnWerkzeugeVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnFassEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnFassVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnBrotEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnBrotVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnTuecherEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnTuecherVk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnKleidungEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnKleidungVk));


        for(int i= 0; i < lokaleHaendlerButtons.size(); i ++){

            lokaleHaendlerButtons.get(i).setText(arrayList.get(i).toString());

        }


    }
}
