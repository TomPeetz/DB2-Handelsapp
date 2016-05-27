package com.example.darkayy.aueraaetas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.spec.ECField;

/**
 * Created by leoka on 27.05.2016.
 */
public class PopUpMarktLokaleHaendler extends Activity{

    Button btnJa;
    Button btnNein;

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
        String rohstoffname = i.getStringExtra("Rohstoffname");
        String rohstoffkostenS = i.getStringExtra("RohstoffkostenK");
        String kosten = i.getStringExtra("Kosten");

        TextView name = (TextView)findViewById(R.id.txtTitel);
        name.setText(rohstoffname + " " + rohstoffkostenS);

        TextView wollenSie = (TextView)findViewById(R.id.txtWollenSieRohstoffe);
        wollenSie.setText("Wollen Sie 1" + rohstoffname);

        TextView fuerKaufen = (TextView)findViewById(R.id.txtFuerGoldKaufen);
        fuerKaufen.setText("für" + kosten + " " + rohstoffkostenS + "?");

        btnJa = (Button)findViewById(R.id.btnJa);
        btnJa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Markt.class);
                startActivity(i);
                finish();
                //Update Lager mit Rohstoff Id
            }
        });


        btnNein = (Button)findViewById(R.id.btnNein);
        btnNein.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Markt.class);
                startActivity(i);
                finish();

                //Update Lager mit Rohstoff Id
            }
        });

    }
}
