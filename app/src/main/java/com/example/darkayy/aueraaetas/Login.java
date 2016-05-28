package com.example.darkayy.aueraaetas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.darkayy.aueraaetas.util.Lagerbestand;
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
                String username = email.getText().toString();
                String password = pw.getText().toString();
                //ToDo: Neuer Login!
                if(Playerdata.login(username,password)){
                    Lagerbestand.getLagerbestand();
                    startService(new Intent(getApplicationContext(), UpdateLagerService.class));
                    Intent i = new Intent(getApplicationContext(), GameUi.class);
                    startActivity(i);
                }

            }
        });
    }
}
