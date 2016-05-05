package com.example.darkayy.aueraaetas;

    import android.content.Intent;
    import android.provider.ContactsContract;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.ImageButton;

public class game_ui extends AppCompatActivity {

        ImageButton buttonP;
        ImageButton buttonL;
        ImageButton buttonG;
        ImageButton buttonM;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_menu);

            buttonP=(ImageButton)findViewById(R.id.btnProfile);
            buttonP.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), profile.class);
                    startActivity(i);
                }
            });

            buttonL=(ImageButton)findViewById(R.id.btnLager);
            buttonL.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), lager.class);
                    startActivity(i);
                }
            });
        }
    }

