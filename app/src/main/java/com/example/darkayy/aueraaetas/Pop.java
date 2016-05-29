package com.example.darkayy.aueraaetas;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.darkayy.aueraaetas.util.Playerdata;
import com.example.darkayy.aueraaetas.webapi.API_Connection;

/**
 * Created by leoka on 22.05.2016.
 */
public class Pop extends Activity{

    NumberPicker picker = null;
    Button btnPreis;
    Button btnErweitern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup);

        picker = (NumberPicker)findViewById(R.id.numberPicker);
        picker.setMaxValue(100);
        picker.setMinValue(0);
        picker.setWrapSelectorWheel(false);

        picker.getValue();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width *.7),(int)(height*.7));

        btnPreis = (Button)findViewById(R.id.btnPreisErrechnen);
        btnPreis.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int value = picker.getValue();
                int preis = 5;
                TextView textView = (TextView)findViewById(R.id.txtKosten);
                textView.setText("Kosten:" + value*preis + " Gold.");
            }
        });

        btnErweitern = (Button)findViewById(R.id.btnerweitern);
        btnErweitern.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                API_Connection con = new API_Connection();
                con.query(API_Connection.HIGHERGEBLVL, new String[]{Integer.toString(Playerdata.getId()), "Lager", Integer.toString(picker.getValue())});
            }
        });
    }
}
