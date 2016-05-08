package com.example.darkayy.aueraaetas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Login extends AppCompatActivity {

    Button button;
    Button login;
    TextView email;
    TextView pw;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        error = (TextView)findViewById(R.id.textView_Loginerror);
        email = (TextView)findViewById(R.id.editText_Email);
        pw = (TextView)findViewById(R.id.editText_Password);
        button = (Button)findViewById(R.id.button_register);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });
        login = (Button)findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //ToDo: Hole den Salt aus der Datenbank vom aktuellen Nutzer
                String[] values = new String[1];
                values[0] = email.getText().toString();
                ArrayList[] salt = JDBC_Connection.doSelect("SELECT salt FROM spieler WHERE email = ?", values);
                //ToDo: Kombiniere Salt mit PW
                String text = pw.getText().toString() + salt[0].get(0);
                //ToDo: SHA-256 Hash aus beiden genieren und mit der Datenbank abgleichen
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    md.update(text.getBytes("UTF-8"));
                    byte[] digest = md.digest();
                    //Formatieren
                    String pwhash = String.format("%064x", new java.math.BigInteger(1, digest));
                    //ToDo: PW Abgleichen
                    ArrayList[] hash = JDBC_Connection.doSelect("SELECT passwort FROM spieler WHERE email = ?", values);
                    if(hash[0].get(0).equals(pwhash)){
                        //ToDo: Login
                        error.setVisibility(View.INVISIBLE);
                    } else {
                        error.setVisibility(View.VISIBLE);
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException u){
                    u.printStackTrace();
                }
            }
        });
    }
}
