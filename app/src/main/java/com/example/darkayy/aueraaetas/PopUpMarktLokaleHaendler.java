package com.example.darkayy.aueraaetas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.TextView;

import java.security.spec.ECField;

/**
 * Created by leoka on 27.05.2016.
 */
public class PopUpMarktLokaleHaendler extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width *.7),(int)(height*.7));


        Intent i = getIntent();
        final String rohstoffname = i.getStringExtra("Rohstoffname");
        int rohstoffid = i.getIntExtra("Rohstoffkosten", 0);
        String rohstoffkostenS = i.getStringExtra("RohstoffkostenK");

        //rohstoffkosten müssen noch über die Id geholt werden
        final int rohstoffkosten = 23;


        TextView name = (TextView)findViewById(R.id.txtTitel);
        name.setText(rohstoffname + rohstoffkostenS);
        final EditText menge = (EditText)findViewById(R.id.editTextMenge);
        final TextView kosten = (TextView)findViewById(R.id.txtRohstoffFuer);
        menge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int temp = Integer.parseInt(menge.getText().toString());
                int kostet = rohstoffkosten * (temp);
                kosten.setText(rohstoffname + "für" + kostet + " Gold." );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
