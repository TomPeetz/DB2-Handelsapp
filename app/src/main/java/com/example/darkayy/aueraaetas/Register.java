package com.example.darkayy.aueraaetas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                JDBC_Connection jdbc = new JDBC_Connection();

                if(!email1.getText().equals(email2.getText())){
                    textview.setText("E-Mail Adresse stimmt nicht überein.");
                    textview.setVisibility(View.VISIBLE);
                } else if (!pw1.getText().equals(pw2.getText())){
                    textview.setText("Passwort stimmt nicht überein.");
                    textview.setVisibility(View.VISIBLE);
                } else {
                    String query = "INSERT INTO spieler (Charaktername, EMAIL, Passwort, salt) + VALUES (?, ?, ?, ?)";
                    String salt = generateSalt();
                    String values[] = new String[4];
                    values[0] = accountname.getText().toString();
                    values[1] = email1.getText().toString();
                    values[2] = generateHash(pw1.getText().toString() + salt);
                    values[3] = salt;
                    /* Disabled until Databse has salt value
                    if(JDBC_Connection.doDML(query, values) == 1){
                        textview.setVisibility(View.INVISIBLE);
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                    } else {
                        textview.setText("Fehler: User existiert bereits!");
                        textview.setVisibility(View.VISIBLE);
                    }
                    */
                }
            }
        });
    }
    private String generateSalt(){
        final Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        String encodedSalt = Base64.encodeToString(salt, Base64.DEFAULT);
        return encodedSalt;
    }
    private  String generateHash(String pw){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pw.getBytes("UTF-8"));
            byte[] digest = md.digest();
            //Formatieren
            String pwhash = String.format("%064x", new java.math.BigInteger(1, digest));
            return pwhash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException u){
            u.printStackTrace();
        }
        return null;
    }
}
