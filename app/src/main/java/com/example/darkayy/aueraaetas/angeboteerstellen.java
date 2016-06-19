package com.example.darkayy.aueraaetas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.darkayy.aueraaetas.util.Lagerbestand;
import com.example.darkayy.aueraaetas.util.Resource;

import java.util.ArrayList;

public class angeboteerstellen extends AppCompatActivity {

    private Spinner spinner;
    private Spinner spinner2;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angeboteerstellen);


        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> list2 = new ArrayList<String>();
        ArrayList<Resource> list = Lagerbestand.getResources();
        for(int i = 0; i < list.size(); i ++){
            list2.add(list.get(i).getName());
        }
        ArrayAdapter<String> adp2=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list2);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp2);

        spinner2 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list2);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adp1);


        btn = (Button) findViewById(R.id.btnTransaktionVeroeffentlichen);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PopupKevin.class);
                startActivity(i);
            }
        });

    }
}
