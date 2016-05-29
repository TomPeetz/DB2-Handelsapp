package com.example.darkayy.aueraaetas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.darkayy.aueraaetas.util.Gebaeudeverwaltung;
import com.example.darkayy.aueraaetas.util.JsonResult;
import com.example.darkayy.aueraaetas.util.Lagerbestand;
import com.example.darkayy.aueraaetas.util.Playerdata;
import com.example.darkayy.aueraaetas.webapi.API_Connection;
import com.example.darkayy.aueraaetas.webapi.API_Exception;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Register extends AppCompatActivity {

    Button button;
    TextView textview;
    TextView accountname;
    TextView pw1;
    TextView pw2;
    TextView email1;
    TextView email2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textview=(TextView)findViewById(R.id.textView);
        accountname=(TextView)findViewById(R.id.editText_charaktername);
        email1=(TextView)findViewById(R.id.editText_reg_email);
        email2=(TextView)findViewById(R.id.editText_reg_emailre);
        pw1=(TextView)findViewById(R.id.editText_reg_pw);
        pw2=(TextView)findViewById(R.id.editText_reg_pwre);
        button=(Button)findViewById(R.id.button_RegisterAcc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email1.getText().toString().equals(email2.getText().toString())){
                    textview.setText("E-Mail Adresse stimmt nicht überein.");
                    textview.setVisibility(View.VISIBLE);
                } else if (!pw1.getText().toString().equals(pw2.getText().toString())){
                    textview.setText("Passwort stimmt nicht überein.");
                    textview.setVisibility(View.VISIBLE);
                } else {

                    String name = accountname.getText().toString();
                    String email = email1.getText().toString();
                    String klarpw = pw1.getText().toString();
                    String[] pwsalt = generateSaltAndPW(klarpw);
                    String salt = pwsalt[1];
                    String pwhash = pwsalt[0];
                    ArrayList<String> params = new ArrayList<String>();
                    API_Connection con = new API_Connection();

                    params.add(API_Connection.APIKEY);
                    params.add(name);
                    params.add(email);
                    params.add(pwhash);
                    params.add(salt);
                    JsonResult result = null;
                    try {
                        System.out.print("REGISTER: Regestriere - Spieledaten: " );
                        for(String s: params){
                            System.out.print(s + ", ");
                        }
                        result = con.query(API_Connection.REGISTER, params);
                    } catch (API_Exception e) {
                        textview.setText("Benutzername/E-Mail bereits registriert!");
                        textview.setVisibility(View.VISIBLE);
                        e.printStackTrace();
                    }
                    System.out.println("REGISTER: " + name + ", " + pw1.getText().toString());
                    if(Playerdata.login(name,klarpw)){
                        Gebaeudeverwaltung.geb4PlayerAnlegen();
                        if(Lagerbestand.createResources()){
                            startService(new Intent(getApplicationContext(), UpdateLagerService.class));
                            Intent i = new Intent(getApplicationContext(), GameUi.class);
                            startActivity(i);
                        }
                    }
                }
            }
        });
    }
    private String[] generateSaltAndPW(String pw){
        API_Connection con = new API_Connection();
        String[] params = {API_Connection.APIKEY, pw, "NULL"};
        JsonResult result = con.query(API_Connection.PWGEN, params);
        String[] exp = {"pw","salt"};
        ArrayList<String> res = result.parseResult(exp);
        String[] pwhash = {res.get(0), res.get(1)};
        return pwhash;
    }
}
