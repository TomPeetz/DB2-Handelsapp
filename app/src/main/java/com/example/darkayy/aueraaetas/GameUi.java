package com.example.darkayy.aueraaetas;

    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.ImageButton;
    import android.widget.TextView;

    import com.example.darkayy.aueraaetas.util.Lagerbestand;
    import com.example.darkayy.aueraaetas.util.Playerdata;
    import com.example.darkayy.aueraaetas.util.Resource;

public class GameUi extends AppCompatActivity {

        ImageButton buttonP;
        ImageButton buttonL;
        ImageButton buttonG;
        ImageButton buttonM;
        TextView    txtProfil;
        TextView    nahrung;
        TextView    stein;
        TextView    gold;
        TextView    holz;
        TextView    lehm;
        TextView    werkzeuge;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game_ui);
            txtProfil=(TextView)findViewById(R.id.txtProfil);
            //ToDo: Findet aus irgendeinem Grund die TextViews nicht ;( Hab aber auch nie Glück...

            nahrung = (TextView)findViewById(R.id.txtrohstoff00);
            stein = (TextView)findViewById(R.id.txtrohstoff10);
            gold = (TextView)findViewById(R.id.txtrohstoff01);
            holz = (TextView)findViewById(R.id.txtrohstoff11);
            lehm = (TextView)findViewById(R.id.txtrohstoff02);
            werkzeuge = (TextView)findViewById(R.id.txtrohstoff12);
            final String stone = ""+Lagerbestand.getRohstoff("Stein").getMenge();
            final String clay = ""+Lagerbestand.getRohstoff("Lehm").getMenge();
            final String skrilla = ""+Lagerbestand.getRohstoff("Goldbarren").getMenge();
            final String wood = ""+Lagerbestand.getRohstoff("Baumstamm").getMenge();
            final String tools = ""+Lagerbestand.getRohstoff("Werkzeuge").getMenge();
            final String food = ""+Lagerbestand.getNahrung();
            werkzeuge.setText(tools);
            lehm.setText(clay);
            holz.setText(wood);
            gold.setText(skrilla);
            stein.setText(stone);
            nahrung.setText(food);
            System.out.println("Test ob Views gefunden werden!");
            System.out.println(nahrung == null);
            System.out.println(stein == null);
            System.out.println(gold == null);
            System.out.println(holz == null);
            System.out.println(lehm == null);
            System.out.println(werkzeuge == null);

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

