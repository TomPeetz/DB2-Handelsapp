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
import android.widget.TextView;

import com.example.darkayy.aueraaetas.util.Lagerbestand;
import com.example.darkayy.aueraaetas.util.Playerdata;
import com.example.darkayy.aueraaetas.webapi.API_Connection;
import com.example.darkayy.aueraaetas.webapi.API_Exception;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Lager extends AppCompatActivity {
    String s = "[{\"Lager_Rohstoff_id\":\"1\",\"Rohstoffname\":\"Golderz\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"2\",\"Rohstoffname\":\"Kohle\",\"Menge\":\"300\"},{\"Lager_Rohstoff_id\":\"3\",\"Rohstoffname\":\"Eisenerz\",\"Menge\":\"2\"},{\"Lager_Rohstoff_id\":\"4\",\"Rohstoffname\":\"Baumstamm\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"5\",\"Rohstoffname\":\"Stein\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"6\",\"Rohstoffname\":\"Lehm\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"7\",\"Rohstoffname\":\"Getreide\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"8\",\"Rohstoffname\":\"Fisch\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"9\",\"Rohstoffname\":\"Fleisch\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"10\",\"Rohstoffname\":\"Wolle\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"11\",\"Rohstoffname\":\"Goldbarren\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"12\",\"Rohstoffname\":\"Eisenbarren\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"13\",\"Rohstoffname\":\"Brett\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"14\",\"Rohstoffname\":\"Ziegelstein\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"15\",\"Rohstoffname\":\"Mehl\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"16\",\"Rohstoffname\":\"Leinen\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"17\",\"Rohstoffname\":\"Waffen\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"18\",\"Rohstoffname\":\"Werkzeuge\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"19\",\"Rohstoffname\":\"Fass\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"20\",\"Rohstoffname\":\"Brot\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"21\",\"Rohstoffname\":\"T\\u00fccher\",\"Menge\":\"20\"},{\"Lager_Rohstoff_id\":\"22\",\"Rohstoffname\":\"Kleidung\",\"Menge\":\"5\"}]";

    ImageButton buttonP;
    ImageButton buttonL;
    ImageButton buttonG;
    ImageButton buttonM;
    ImageButton buttonH;

    Button btnPopup;
    int maxLagerGroesse = 2000;
    ArrayList<TextView> lager = new ArrayList<TextView>();
    ArrayList<TextView> lagerG = new ArrayList<TextView>();

    ArrayList<Button> popUp = new ArrayList<Button>();

    ArrayList select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lager);
        System.out.println(s);
        Lagerbestand.getLagerbestand();
        fillMenge(Lagerbestand.getMengen());
        fillLagerCounter(Lagerbestand.getMengen());

        buttonP = (ImageButton) findViewById(R.id.btnProfile);
        buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Profil.class);
                startActivity(i);
            }
        });

        buttonL = (ImageButton) findViewById(R.id.btnLager);
        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Lager.class);
                startActivity(i);
            }
        });

        buttonM = (ImageButton) findViewById(R.id.btnMarkt);
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Markt.class);
                startActivity(i);
            }
        });

        buttonG = (ImageButton) findViewById(R.id.btnGebaude);
        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Gebaude.class);
                startActivity(i);
            }
        });

        btnPopup = (Button) findViewById(R.id.btnLagerErweitern);
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Pop.class);
                startActivity(i);
            }
        });

        buttonH=(ImageButton)findViewById(R.id.btnHome);
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GameUi.class);
                startActivity(i);
            }
        });
    }
    /* ToDo: Verbesserungsvorschlag für Später:
             Buttons Dynamisch genieren anhand des Datenbankoutputs.

             Button myButton = new Button(this);
             myButton.setText("Push Me");

             LinearLayout ll = (LinearLayout)findViewById(R.id.buttonlayout);
             LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
             ll.addView(myButton, lp);

             Tut mir leid Leo *wegduck* ;D
     */
    public void fillLager(ArrayList<Integer> a) {
        select = a;
        lagerG.add((TextView) findViewById(R.id.txtGolderz));
        lagerG.add((TextView) findViewById(R.id.txtGoldMenge));
        lagerG.add((TextView) findViewById(R.id.txtKohle));
        lagerG.add((TextView) findViewById(R.id.txtKohleMenge));
        lagerG.add((TextView) findViewById(R.id.txtEisenerz));
        lagerG.add((TextView) findViewById(R.id.txtEisenerzMenge));
        lagerG.add((TextView) findViewById(R.id.txtBaumstamm));
        lagerG.add((TextView) findViewById(R.id.txtBaumstammMenge));
        lagerG.add((TextView) findViewById(R.id.txtStein));
        lagerG.add((TextView) findViewById(R.id.txtSteinMenge));
        lagerG.add((TextView) findViewById(R.id.txtLehm));
        lagerG.add((TextView) findViewById(R.id.txtLehmMenge));
        lagerG.add((TextView) findViewById(R.id.txtGetreide));
        lagerG.add((TextView) findViewById(R.id.txtGetreideMenge));
        lagerG.add((TextView) findViewById(R.id.txtFisch));
        lagerG.add((TextView) findViewById(R.id.txtFischMenge));
        lagerG.add((TextView) findViewById(R.id.txtFleisch));
        lagerG.add((TextView) findViewById(R.id.txtFleischMenge));
        lagerG.add((TextView) findViewById(R.id.txtWolle));
        lagerG.add((TextView) findViewById(R.id.txtWolleMenge));
        lagerG.add((TextView) findViewById(R.id.txtGoldbarren));
        lagerG.add((TextView) findViewById(R.id.txtGoldbarrenMenge));
        lagerG.add((TextView) findViewById(R.id.txtEisenbarren));
        lagerG.add((TextView) findViewById(R.id.txtEisenbarrenMenge));
        lagerG.add((TextView) findViewById(R.id.txtBrett));
        lagerG.add((TextView) findViewById(R.id.txtBrettMenge));
        lagerG.add((TextView) findViewById(R.id.txtZiegelstein));
        lagerG.add((TextView) findViewById(R.id.txtZiegelsteinMenge));
        lagerG.add((TextView) findViewById(R.id.txtMehl));
        lagerG.add((TextView) findViewById(R.id.txtMehlMenge));
        lagerG.add((TextView) findViewById(R.id.txtLeinen));
        lagerG.add((TextView) findViewById(R.id.txtLeinenMenge));
        lagerG.add((TextView) findViewById(R.id.txtWaffen));
        lagerG.add((TextView) findViewById(R.id.txtWaffenMenge));
        lagerG.add((TextView) findViewById(R.id.txtWerkzeuge));
        lagerG.add((TextView) findViewById(R.id.txtWerkzeugeMenge));
        lagerG.add((TextView) findViewById(R.id.txtFass));
        lagerG.add((TextView) findViewById(R.id.txtFassMenge));
        lagerG.add((TextView) findViewById(R.id.txtBrot));
        lagerG.add((TextView) findViewById(R.id.txtBrotMenge));
        lagerG.add((TextView) findViewById(R.id.txtTuecher));
        lagerG.add((TextView) findViewById(R.id.txtTuecherMenge));
        lagerG.add((TextView) findViewById(R.id.txtKleidung));
        lagerG.add((TextView) findViewById(R.id.txtKleidungMenge));

        if(select.size() != lager.size()){
            System.out.print("Parameteranzahl zu gering!");
        }

        else {
            for (int i = 0; i < lager.size(); i++) {
                lager.get(i).setText(select.get(i).toString());
            }
        }


    }

    public void fillLagerCounter(ArrayList<Integer> a){
        int menge = 0;
        for(int i = 0; i < a.size(); i++){
            menge += a.get(i);
        }
        TextView txt = (TextView) findViewById(R.id.txtGroesse);
        txt.setText(menge + "/" + maxLagerGroesse);
    }

    public void fillMenge(ArrayList<Integer> a) {
        select = a;
        lager.add((TextView) findViewById(R.id.txtGoldMenge));
        lager.add((TextView) findViewById(R.id.txtKohleMenge));
        lager.add((TextView) findViewById(R.id.txtEisenerzMenge));
        lager.add((TextView) findViewById(R.id.txtBaumstammMenge));
        lager.add((TextView) findViewById(R.id.txtSteinMenge));
        lager.add((TextView) findViewById(R.id.txtLehmMenge));
        lager.add((TextView) findViewById(R.id.txtGetreideMenge));
        lager.add((TextView) findViewById(R.id.txtFischMenge));
        lager.add((TextView) findViewById(R.id.txtFleischMenge));
        lager.add((TextView) findViewById(R.id.txtWolleMenge));
        lager.add((TextView) findViewById(R.id.txtGoldbarrenMenge));
        lager.add((TextView) findViewById(R.id.txtEisenbarrenMenge));
        lager.add((TextView) findViewById(R.id.txtBrettMenge));
        lager.add((TextView) findViewById(R.id.txtZiegelsteinMenge));
        lager.add((TextView) findViewById(R.id.txtMehlMenge));
        lager.add((TextView) findViewById(R.id.txtLeinenMenge));
        lager.add((TextView) findViewById(R.id.txtWaffenMenge));
        lager.add((TextView) findViewById(R.id.txtWerkzeugeMenge));
        lager.add((TextView) findViewById(R.id.txtFassMenge));
        lager.add((TextView) findViewById(R.id.txtBrotMenge));
        lager.add((TextView) findViewById(R.id.txtTuecherMenge));
        lager.add((TextView) findViewById(R.id.txtKleidungMenge));

        if(select.size() != lager.size()){
            System.out.print("Parameteranzahl zu gering!");
        }

        else {
            for (int i = 0; i < lager.size(); i++) {
                lager.get(i).setText(select.get(i).toString());
            }

        }


    }

    public void fillLagerPopup(ArrayList arrayList){

        popUp.add((Button) findViewById(R.id.btnPreis0));
        popUp.add((Button) findViewById(R.id.btnPreis1));
        popUp.add((Button) findViewById(R.id.btnPreis2));

        for(int i = 0; i < popUp.size(); i++){
            popUp.get(i).setText(arrayList.get(i).toString());
        }
    }

}
