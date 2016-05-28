package com.example.darkayy.aueraaetas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.darkayy.aueraaetas.util.JsonResult;
import com.example.darkayy.aueraaetas.util.Lagerbestand;
import com.example.darkayy.aueraaetas.util.Resource;
import com.example.darkayy.aueraaetas.webapi.API_Connection;
import com.example.darkayy.aueraaetas.webapi.API_Exception;

import java.lang.reflect.Array;
import java.security.spec.ECField;
import java.util.ArrayList;

/**
 * Created by leoka on 27.05.2016.
 */
public class PopUpMarktLokaleHaendler extends Activity{

    Button btnJa;
    Button btnNein;
    TextView resourcebestand;
    TextView goldbestand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.markt_lokalehaendler);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width *.7),(int)(height*.7));
        Intent i = getIntent();
        resourcebestand = (TextView)findViewById(R.id.txtimLagerinput);
        goldbestand = (TextView)findViewById(R.id.txtGold);
        final String rohstoffname = i.getStringExtra("Rohstoffname");
        Resource gold = Lagerbestand.getRohstoff("Goldbarren");
        Resource diese = Lagerbestand.getRohstoff(rohstoffname);
        System.out.println("TEST: " + gold.getName());
        resourcebestand.setText(diese.getMenge() + " "  + diese.getName());
        goldbestand.setText(gold.getMenge() + " " + gold.getName());
        API_Connection conn = new API_Connection();
        int id = Lagerbestand.getRohstoff(rohstoffname).getId();
        ArrayList<String> params = new ArrayList<String>();
        params.add(API_Connection.APIKEY);
        params.add(""+id);
        String kosten = "";
        final String rohstoffkostenS = i.getStringExtra("RohstoffkostenK");
        try {
            if(rohstoffkostenS.equals("kaufen")){
                JsonResult res = conn.query(API_Connection.GETBUYPRICE, params);
                String[] exp = {"preis"};
                String price = res.parseResult(exp).get(0);
                kosten = ""+ price;
            } else {
                JsonResult res = conn.query(API_Connection.GETSELLPRICE, params);
                String[] exp = {"preis"};
                String price = res.parseResult(exp).get(0);
                kosten = ""+ price;
            }


        } catch (API_Exception e) {
            e.printStackTrace();
        }
        //Kaufen oder verkaufen für die Logik


        int rohstoffID = i.getIntExtra("RohstoffId", 1);

        TextView name = (TextView)findViewById(R.id.txtTitel);
        name.setText(rohstoffname + " " + rohstoffkostenS);

        TextView wollenSie = (TextView)findViewById(R.id.txtWollenSieRohstoffe);
        wollenSie.setText("Wollen Sie 1" + rohstoffname);

        TextView fuerKaufen = (TextView)findViewById(R.id.txtFuerGoldKaufen);
        fuerKaufen.setText("für " + kosten + " " + rohstoffkostenS + "?");

        btnJa = (Button)findViewById(R.id.btnerweitern);
        btnJa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Update Lager mit Rohstoff Id
                //ToDo: Warum zur HÖLLE findet er den Rohstoff nicht xD!
                //Antwort: Lager war nicht geupdatet ich fühle mich doof....

                System.out.println("Testabruf: " + Lagerbestand.getRohstoff(1).getName());
                int id = Lagerbestand.getRohstoff(rohstoffname).getId();
                API_Connection con  = new API_Connection();
                ArrayList<String> params = new ArrayList<String>();
                params.add(API_Connection.APIKEY);
                params.add(""+id);
                Resource gold = Lagerbestand.getRohstoff("Goldbarren");
                Resource other = Lagerbestand.getRohstoff(id);

                try {
                    if(rohstoffkostenS.equals("kaufen")){
                        JsonResult res = con.query(API_Connection.GETBUYPRICE, params);
                        String[] exp = {"preis"};
                        int preis = -1*Integer.parseInt(res.parseResult(exp).get(0));
                        System.out.println("Kaufe: " + other.getName() + " für " + preis + " Goldbarren");
                        Lagerbestand.changeAmount(gold.getId(), preis);
                        Lagerbestand.changeAmount(other.getId(), 1);
                    } else {
                        JsonResult res = con.query(API_Connection.GETSELLPRICE, params);
                        String[] exp = {"preis"};
                        int preis = Integer.parseInt(res.parseResult(exp).get(0));
                        System.out.println("Verkaufe: " + other.getName() + " für " + preis + " Goldbarren");
                        Lagerbestand.changeAmount(gold.getId(), preis);
                        Lagerbestand.changeAmount(other.getId(), -1);
                    }

                } catch (API_Exception e) {
                    e.printStackTrace();
                }


                Intent i = new Intent(getApplicationContext(), Markt.class);
                startActivity(i);
                finish();

            }
        });


        btnNein = (Button)findViewById(R.id.btnNein);
        btnNein.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Markt.class);
                startActivity(i);
                finish();
            }
        });

    }
}
