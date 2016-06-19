package com.example.darkayy.aueraaetas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.darkayy.aueraaetas.util.Gebaeude;
import com.example.darkayy.aueraaetas.util.Gebaeudeverwaltung;
import com.example.darkayy.aueraaetas.util.Lagerbestand;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Gebaude extends AppCompatActivity {

    private ImageButton buttonP;
    private ImageButton buttonL;
    private ImageButton buttonG;
    private ImageButton buttonM;
    private ImageButton buttonH;
    private Button btnPopup;

    private ArrayList<TextView> prodKosten = new ArrayList<TextView>();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    static ArrayList<String> speicher = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gebaude);



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

        /*btnPopup=(Button)findViewById(R.id.btnGoldmine);
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), PopGebaeude.class);
                startActivity(i);

            }
        });*/

        buttonH=(ImageButton)findViewById(R.id.btnHome);
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GameUi.class);
                startActivity(i);

            }
        });

        fillArrays();
        ArrayList<String> gebInfos = new ArrayList<String>();
        ArrayList<Gebaeude> gebaeude = Gebaeudeverwaltung.getGebaeudeList();
        for (Gebaeude g: gebaeude) {
            gebInfos.add(g.getBeschreibung());
        }
        setBtns(gebInfos);


    }

    public void fillArrays(){
        //produktionsKosten
        prodKosten.add((TextView) findViewById(R.id.txtGoldmineProd));
        prodKosten.add((TextView) findViewById(R.id.txtGoldmineK));
        prodKosten.add((TextView) findViewById(R.id.txtGoldschmelzePr));
        prodKosten.add((TextView) findViewById(R.id.txtGoldschmelzeK));
        prodKosten.add((TextView) findViewById(R.id.txtKohlemineP));
        prodKosten.add((TextView) findViewById(R.id.txtKohlemineK));
        prodKosten.add((TextView) findViewById(R.id.txtEisenmineP));
        prodKosten.add((TextView) findViewById(R.id.txtEisenmineK));
        prodKosten.add((TextView) findViewById(R.id.txtEisenschmelzeP));
        prodKosten.add((TextView) findViewById(R.id.txtEisenschmelzeK));
        prodKosten.add((TextView) findViewById(R.id.txtWaffenschmiedeP));
        prodKosten.add((TextView) findViewById(R.id.txtWaffenschmiedeK));
        prodKosten.add((TextView) findViewById(R.id.txtWerkzeugschmiedeP));
        prodKosten.add((TextView) findViewById(R.id.txtWerkzeugschmiedeK));
        prodKosten.add((TextView) findViewById(R.id.txtHolzfaellerP));
        prodKosten.add((TextView) findViewById(R.id.txtHolzfaellerK));
        prodKosten.add((TextView) findViewById(R.id.txtSaegeP));
        prodKosten.add((TextView) findViewById(R.id.txtSaegeK));
        prodKosten.add((TextView) findViewById(R.id.txtSchreinerP));
        prodKosten.add((TextView) findViewById(R.id.txtSchreinerK));
        prodKosten.add((TextView) findViewById(R.id.txtSteinbruchP));
        prodKosten.add((TextView) findViewById(R.id.txtSteinbruchK));
        prodKosten.add((TextView) findViewById(R.id.txtLehmgrubeP));
        prodKosten.add((TextView) findViewById(R.id.txtLehmgrubeK));
        prodKosten.add((TextView) findViewById(R.id.txtZiegeleiP));
        prodKosten.add((TextView) findViewById(R.id.txtZiegeleiK));
        prodKosten.add((TextView) findViewById(R.id.txtBauernhofP));
        prodKosten.add((TextView) findViewById(R.id.txtBauernhofK));
        prodKosten.add((TextView) findViewById(R.id.txtWindP));
        prodKosten.add((TextView) findViewById(R.id.txtWindK));
        prodKosten.add((TextView) findViewById(R.id.txtBaeckerP));
        prodKosten.add((TextView) findViewById(R.id.txtBaeckerK));
        prodKosten.add((TextView) findViewById(R.id.txtFischerP));
        prodKosten.add((TextView) findViewById(R.id.txtFischerK));
        prodKosten.add((TextView) findViewById(R.id.txtJaegerP));
        prodKosten.add((TextView) findViewById(R.id.txtJaegerK));
        prodKosten.add((TextView) findViewById(R.id.txtSchafsfarmP));
        prodKosten.add((TextView) findViewById(R.id.txtSchafsfarmK));
        prodKosten.add((TextView) findViewById(R.id.txtWEbereiEP));
        prodKosten.add((TextView) findViewById(R.id.txtWebereiEK));
        prodKosten.add((TextView) findViewById(R.id.txtWebereiMP));
        prodKosten.add((TextView) findViewById(R.id.txtWebereiMK));
        prodKosten.add((TextView) findViewById(R.id.txtWebereiFP));
        prodKosten.add((TextView) findViewById(R.id.txtWebereiFK));

        //buttons
        buttons.add((Button)findViewById(R.id.btnGoldmine));
        buttons.add((Button)findViewById(R.id.btnGoldschmelze));
        buttons.add((Button)findViewById(R.id.btnKohlemine));
        buttons.add((Button)findViewById(R.id.btnEisenmine));
        buttons.add((Button)findViewById(R.id.btnEisenschmelze));
        buttons.add((Button)findViewById(R.id.btnWaffenschmiede));
        buttons.add((Button)findViewById(R.id.btnWerkzeugschmiede));
        buttons.add((Button)findViewById(R.id.btnHolzfaellerlager));
        buttons.add((Button)findViewById(R.id.btnSaegemuehle));
        buttons.add((Button)findViewById(R.id.btnSchreiner));
        buttons.add((Button)findViewById(R.id.btnSteinbruch));
        buttons.add((Button)findViewById(R.id.btnLehmgrube));
        buttons.add((Button)findViewById(R.id.btnZiegelei));
        buttons.add((Button)findViewById(R.id.btnBauernhof));
        buttons.add((Button)findViewById(R.id.btnWindmuehle));
        buttons.add((Button)findViewById(R.id.btnBaecker));
        buttons.add((Button)findViewById(R.id.btnFischer));
        buttons.add((Button)findViewById(R.id.btnJaeger));
        buttons.add((Button)findViewById(R.id.btnSchafsfarm));
        buttons.add((Button)findViewById(R.id.btnWebereiE));
        buttons.add((Button)findViewById(R.id.btnWEbereiM));
        buttons.add((Button)findViewById(R.id.btnWebereiF));
    }

    public void setKostenProduziert(ArrayList arrayList) {



        for (int i = 0; i < prodKosten.size(); i++) {
            prodKosten.get(i).setText(arrayList.get(i).toString());
        }
    }


    public void setBtns( ArrayList<String> gebaeudeinfos){


        for(int i = 0; i < buttons.size(); i++){

            final Button temp = buttons.get(i);
            final String s = gebaeudeinfos.get(i).toString();



            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PopGebaeude.class);

                    intent.putExtra("Gebäudename",temp.getText().toString());
                    intent.putExtra("Gebaeudeinfos",s);


                    startActivity(intent);
                }
            });

        }


        }


    public static void clearSpeicher(){

        speicher.clear();

    }

    }

