package com.example.darkayy.aueraaetas;

    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.ImageButton;
    import android.widget.TextView;

    import com.example.darkayy.aueraaetas.util.Playerdata;

public class GameUi extends AppCompatActivity {

        ImageButton buttonP;
        ImageButton buttonL;
        ImageButton buttonG;
        ImageButton buttonM;
        TextView    txtProfil;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game_ui);
            txtProfil=(TextView)findViewById(R.id.txtProfil);
            txtProfil.setText(Playerdata.getUsername());
            buttonP=(ImageButton)findViewById(R.id.btnProfile);
            buttonP.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Profil.class);
                    startActivity(i);


                }
            });

            buttonL=(ImageButton)findViewById(R.id.btnLager);
            buttonL.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Lager.class);
                    startActivity(i);

                }
            });

            buttonM=(ImageButton)findViewById(R.id.btnMarkt);
            buttonM.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Markt.class);
                    startActivity(i);

                }
            });

            buttonG=(ImageButton)findViewById(R.id.btnGebaude);
            buttonG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Gebaude.class);
                    startActivity(i);

                }
            });
        }
    }

