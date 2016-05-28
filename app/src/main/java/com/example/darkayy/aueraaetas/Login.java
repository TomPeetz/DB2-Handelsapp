package com.example.darkayy.aueraaetas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.darkayy.aueraaetas.util.Playerdata;
import com.example.darkayy.aueraaetas.webapi.API_Connection;
import com.example.darkayy.aueraaetas.webapi.API_Exception;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Login extends AppCompatActivity {

    Button button;
    Button login;
    Button test;
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
                //Hole den Salt aus der Datenbank vom aktuellen Nutzer

                String username = email.getText().toString();
                API_Connection con = new API_Connection();
                //Test
                ArrayList<String> params2 = new ArrayList<String>();
                params2.add(API_Connection.APIKEY);
                params2.add("4");
                try {
                    ArrayList<String> result = con.query(API_Connection.GETLAGER, params2);
                    for(String s: result){
                        System.out.println(s);
                    }
                } catch (API_Exception e) {
                    e.printStackTrace();
                }
                //Test
                ArrayList<String> params = new ArrayList<String>();
                params.add(API_Connection.APIKEY);
                params.add(username);
                ArrayList<String> result = null;
                try {
                    result = con.query(API_Connection.GETSALT, params);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Kombiniere Salt mit PW

                String text = null;
                text = pw.getText().toString() + result.get(0);
                System.out.println(text);


                //SHA-256 Hash aus beiden genieren und mit der Datenbank abgleichen

                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    md.update(text.getBytes("UTF-8"));
                    byte[] digest = md.digest();
                    //Formatieren
                    String pwhash = String.format("%064x", new java.math.BigInteger(1, digest));
                    System.out.println("Passwort: " + pwhash);
                    //ToDo: PW Abgleichen & API_Connection in Callable umwandeln
                    params = new ArrayList<String>();
                    params.add(API_Connection.APIKEY);
                    params.add(username);
                    params.add(pwhash);
                    try {
                        result = con.query(API_Connection.LOGIN, params);
                    } catch (API_Exception e) {
                        e.printStackTrace();
                    }
                    // Wenn Login erfolgreich war
                    if(!result.isEmpty()){
                        //ToDo: GameUi View öffnen!
                        createPlayerdata(result);
                        if(Playerdata.isValid()){
                            Intent i = new Intent(getApplicationContext(), GameUi.class);
                            startActivity(i);
                        }
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException u){
                    u.printStackTrace();
                }

            }
        });
    }

    private void createPlayerdata(ArrayList<String> spielerdaten){
        int count = 0;
        String id = spielerdaten.get(count++);
        String username = spielerdaten.get(count++);
        String email = spielerdaten.get(count++);
        String  pwhash = spielerdaten.get(count++);
        String  salt = spielerdaten.get(count++);
        String  lastLogin = spielerdaten.get(count++);
        String isBanned = spielerdaten.get(count++);
        String  profilePic = spielerdaten.get(count++);
        Playerdata.createPlayerdata("1","username","email","pwhash","salt","lastLogin","0","profilePic");
    }
}
