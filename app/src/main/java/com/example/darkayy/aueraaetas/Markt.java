package com.example.darkayy.aueraaetas;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.darkayy.aueraaetas.util.JsonResult;
import com.example.darkayy.aueraaetas.util.Lagerbestand;
import com.example.darkayy.aueraaetas.util.Playerdata;
import com.example.darkayy.aueraaetas.util.Resource;
import com.example.darkayy.aueraaetas.webapi.API_Connection;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Markt extends AppCompatActivity {

    private ImageButton buttonP;
    private ImageButton buttonL;
    private ImageButton buttonG;
    private ImageButton buttonM;
    private ImageButton buttonH;

    //Transaktion erstellen
    private Spinner spinner;
    private Spinner spinner2;
    private EditText editText;
    private EditText editText2;
    private Button btn;

    private ArrayList<Button> lokaleHaendlerButtons = new ArrayList<Button>();
    private ArrayList<Button> lokaleHaendlerButtonsEk = new ArrayList<Button>();
    private ArrayList<TextView> lokaleHaendlerTxts = new ArrayList<TextView>();
    private static ArrayList<TextView> angeboteSuchenName = new ArrayList<TextView>();
    private static ArrayList<TextView> angeboteSuchenMenge = new ArrayList<TextView>();
    private static ArrayList<TextView> angeboteSuchenRohstoff = new ArrayList<TextView>();
    private static ArrayList<Button> angeboteSuchenBtns= new ArrayList<Button>();

    private Button transaktion;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markt);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();


        TabHost.TabSpec tabSpec = tabHost.newTabSpec("lokaleHaendler");
        tabSpec.setContent(R.id.lokaleHaendler);
        tabSpec.setIndicator("Lokale Händler");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("angeboteErstellen");
        tabSpec.setContent(R.id.angeboteErstellen);
        tabSpec.setIndicator("Angebote erstellen");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("angeboteSuchen");
        tabSpec.setContent(R.id.angeboteSuchen);
        tabSpec.setIndicator("Angebote suchen");
        tabHost.addTab(tabSpec);

        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#000000"));
        }


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

        buttonH=(ImageButton)findViewById(R.id.btnHome);
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GameUi.class);
                startActivity(i);
            }
        });



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


        fillArrays();
        for(int i = 0 ; i < angeboteSuchenName.size(); i ++ ){
            angeboteSuchenName.get(i).setText("");
        }

        for(int i = 0; i < angeboteSuchenBtns.size(); i++){
            angeboteSuchenBtns.get(i).setText("");
        }
        for(int i = 0; i < angeboteSuchenRohstoff.size(); i++){
            angeboteSuchenRohstoff.get(i).setText("");
        }
        setAngeboteSuchenText();

        transaktion = (Button)findViewById(R.id.btnTransaktionVeroeffentlichen);
        transaktion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int rohstoffBiete = Lagerbestand.getRohstoff(spinner.getSelectedItem().toString()).getId();
                int rohstoffMoechte = Lagerbestand.getRohstoff(spinner2.getSelectedItem().toString()).getId();

                editText = (EditText)findViewById(R.id.editTextMengeBieteAn);
                editText2 = (EditText)findViewById(R.id.editTextMoechte);

                String mengeBieteAn = editText.getText().toString();
                String mengeMoechte = editText2.getText().toString();

                System.out.println(rohstoffBiete + "  " + rohstoffMoechte);

                if(Lagerbestand.getRohstoff(rohstoffBiete).getMenge() < Integer.parseInt(mengeBieteAn)){
                    Intent i = new Intent(getApplicationContext(), PopupKevin.class);
                    startActivity(i);
                }
                else {

                    API_Connection con = new API_Connection();
                    String[] s = {API_Connection.APIKEY, "" + Playerdata.getId(), "" + rohstoffBiete, "" + rohstoffMoechte, mengeBieteAn, mengeMoechte};
                    JsonResult res = con.query(API_Connection.TRANSAKTIONVEROEFFENTLICHEN, s);
                    System.out.println(res.isEmpty());

                }
                    setAngeboteSuchenText();
                    editText.setText("");
                    editText2.setText("");

            }
        });


        API_Connection con2 = new API_Connection();
        String [] s = {API_Connection.APIKEY, "" + Playerdata.getId()};
        con2.query(API_Connection.GETTRANSAKTION, s);

        JsonResult res = con2.query(API_Connection.GETTRANSAKTION, s);
        if(res == null){

        }
        else {
            String[] exp1 = {"Transaktions_ID", "Spieler_ID_Verkaeufer", "Charaktername", "Rohstoff_ID_Angebot", "Rohstoff_ID_Gefordert", "Gefordert_Menge", "Angebot_Menge", "erstellt_am"};
            ArrayList<String> result = new ArrayList<String>();
            ArrayList<String> result1 = new ArrayList<String>();
            ArrayList<String> result2 = new ArrayList<String>();
            while (!res.isEmpty()) {
                result = res.parseResult(exp1);
                result1.add(result.get(0));
                result2.add(result.get(1));
            }


            for (int i = 0; i < angeboteSuchenBtns.size(); i++) {
                System.out.println("BLALAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA:" + result1.size() + " " + result2.size() + "  " + i);
                if (i == result1.size() && i == result2.size()) {
                    System.out.println("ICH heiße KEVIN und ich bin scheiße und ich BREAKE JETZT!");
                    break;
                }

                final String temp = result1.get(i);
                final String temp2 = result2.get(i);
                angeboteSuchenBtns.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), PopupMarkt.class);
                        i.putExtra("ID", temp);
                        i.putExtra("SpielerID", temp2);
                        startActivity(i);
                    }
                });

            }
        }



        setLokaleHaendlerButtonsEk();
        setLokaleHaendlerButtonsVk();

    }

    /*
    Die ArrayList von dem SELECT:
    SELECT Bot_Einkaufspreis, Bot_Verkaufspreis FROM Rohstoffe;

     */

    public void fillLokaleHaendler(ArrayList<String> arrayList){

        for(int i= 0; i < lokaleHaendlerButtons.size(); i ++){

            lokaleHaendlerButtons.get(i).setText(arrayList.get(i).toString());

        }


    }

    public void fillArrays(){

        /*lokale Händler*/
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnGolderzEinkauf));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnGolderzVerkauf));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnKohleEinkauf));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnKohleVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnEisenerzEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnEisenerzVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnBaumstammEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnBaumstammVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnSteinEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnSteinVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnLehmEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnLehmVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnGetreideEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnGetreideVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnFischEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnFischVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnFleischEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnFleischVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnWolleEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnWolleVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnGoldbarrenEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnGoldbarrenVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnEisenbarrenEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnEisenbarrenVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnBrettEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnBrettVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnZiegelsteinEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnZiegelsteinVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnMehlEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnMehlVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnLeinenEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnLeinenVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnWaffenEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnWaffenVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnWerkzeugeEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnWerkzeugeVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnFassEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnFassVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnBrotEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnBrotVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnTuecherEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnTuecherVk));
        lokaleHaendlerButtonsEk.add((Button)findViewById(R.id.btnKleidungEk));
        lokaleHaendlerButtons.add((Button)findViewById(R.id.btnKleidungVk));

        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtGolderz));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtKohle));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtEisenerz));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtBaumstamm));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtStein));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtLehm));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtGetreide));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtFisch));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtFleisch));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtWolle));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtGoldbarren));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtEisenbarren));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtBrett));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtZiegelstein));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtMehl));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtLeinen));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtWaffen));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtWerkzeuge));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtFass));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtBrot));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtTuecher));
        lokaleHaendlerTxts.add((TextView)findViewById(R.id.txtKleidung));

        //angebote Suchen
        angeboteSuchenName.add((TextView)findViewById(R.id.txtName1));
        angeboteSuchenRohstoff.add((TextView)findViewById(R.id.txtRohstoffSuche1));
        angeboteSuchenName.add((TextView)findViewById(R.id.txtName2));
        angeboteSuchenRohstoff.add((TextView)findViewById(R.id.txtRohstoffSuche2));
        angeboteSuchenName.add((TextView)findViewById(R.id.txtName3));
        angeboteSuchenRohstoff.add((TextView)findViewById(R.id.txtRohstoffSuche3));
        angeboteSuchenName.add((TextView)findViewById(R.id.txtName4));
        angeboteSuchenRohstoff.add((TextView)findViewById(R.id.txtRohstoffSuche4));
        angeboteSuchenName.add((TextView)findViewById(R.id.txtName5));
        angeboteSuchenRohstoff.add((TextView)findViewById(R.id.txtRohstoffSuche5));
        angeboteSuchenName.add((TextView)findViewById(R.id.txtName6));
        angeboteSuchenRohstoff.add((TextView)findViewById(R.id.txtRohstoffSuche6));
        angeboteSuchenName.add((TextView)findViewById(R.id.txtName7));
        angeboteSuchenRohstoff.add((TextView)findViewById(R.id.txtRohstoffSuche7));
        angeboteSuchenName.add((TextView)findViewById(R.id.txtName8));
        angeboteSuchenRohstoff.add((TextView)findViewById(R.id.txtRohstoffSuche8));


        angeboteSuchenBtns.add((Button)findViewById(R.id.btnPreis1));
        angeboteSuchenBtns.add((Button)findViewById(R.id.btnPreis2));
        angeboteSuchenBtns.add((Button)findViewById(R.id.btnPreis3));
        angeboteSuchenBtns.add((Button)findViewById(R.id.btnPreis4));
        angeboteSuchenBtns.add((Button)findViewById(R.id.btnPreis5));
        angeboteSuchenBtns.add((Button)findViewById(R.id.btnPreis6));
        angeboteSuchenBtns.add((Button)findViewById(R.id.btnPreis7));
        angeboteSuchenBtns.add((Button)findViewById(R.id.btnPreis8));

    }

    public static void setAngeboteSuchenText(){


        API_Connection con2 = new API_Connection();
        String [] s = {API_Connection.APIKEY, "" + Playerdata.getId()};

        JsonResult res = con2.query(API_Connection.GETTRANSAKTION, s);

        String[] exp = {"Charaktername", "Rohstoff_ID_Angebot", "Rohstoff_ID_Gefordert", "Gefordert_Menge","Angebot_Menge" , "erstellt_am", "Spieler_ID_Verkaeufer", "Transaktions_ID" };
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> spieler_id = new ArrayList<String>();
        ArrayList<String> rohstoff_angebot = new ArrayList<String>();
        ArrayList<String> rohstoff_gefordert = new ArrayList<String>();
        ArrayList<String> gefordert_menge = new ArrayList<String>();
        ArrayList<String> angebot_menge = new ArrayList<String>();



        if(res == null){
            System.out.print("!ICH RETURNE!");
            return;
        }

        while(!res.isEmpty()) {
            temp = res.parseResult(exp);
            for(String s5 : temp){
                System.out.println("PARSE:!!!! X;X;X;X Q_Q:" + s5);
            }
            spieler_id.add(temp.get(0));
            rohstoff_angebot.add(temp.get(1));
            rohstoff_gefordert.add(temp.get(2));
            gefordert_menge.add(temp.get(3));
            angebot_menge.add(temp.get(4));
        }


        int count = 0;
        int count1 = 0;
        int count2 = 0;



        for(String s1 : spieler_id){
            angeboteSuchenName.get(count).setText(s1);
            count +=1;
            System.out.println("Spieler id: " + s1);
        }
        for(String s2 : rohstoff_gefordert){
            angeboteSuchenBtns.get(count1).setText("" +  gefordert_menge.get(count1) + " " +
            Lagerbestand.getRohstoff(Integer.parseInt(s2)).getName());
            System.out.println("Rohstoff gefordert ID: " + s2);
            count1 +=1;
        }

        for(String s3 : rohstoff_angebot){
            angeboteSuchenRohstoff.get(count2).setText("" + gefordert_menge.get(count2) +
            Lagerbestand.getRohstoff(Integer.parseInt(s3)).getName());
            System.out.println("Rohstoff angebot id: " + s3);
            count2 +=1;
        }


    }

    public void setLokaleHaendlerButtonsEk(){

        for(int i = 0; i < lokaleHaendlerButtonsEk.size(); i ++) {

            final String name = lokaleHaendlerTxts.get(i).getText().toString();
            final String kosten = lokaleHaendlerButtonsEk.get(i).getText().toString();
            final String TitelS = "kaufen";
            final int rohstoffId = i + 1;
            lokaleHaendlerButtonsEk.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PopUpMarktLokaleHaendler.class);

                    intent.putExtra("Rohstoffname", name);
                    intent.putExtra("Kosten", kosten);
                    intent.putExtra("RohstoffkostenK", TitelS);
                    intent.putExtra("RohstoffId", rohstoffId);

                    startActivity(intent);
                }
            });
        }
    }

    public void setLokaleHaendlerButtonsVk(){

        for(int i = 0; i < lokaleHaendlerButtons.size(); i ++){

            final String name = lokaleHaendlerTxts.get(i).getText().toString();
            final String kosten = lokaleHaendlerButtons.get(i).getText().toString();
            final String TitelS = "verkaufen";
            final int rohstoffId = i + 1;
            lokaleHaendlerButtons.get(i).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PopUpMarktLokaleHaendler.class);

                    intent.putExtra("Rohstoffname",name);
                    intent.putExtra("Kosten",kosten);
                    intent.putExtra("RohstoffkostenK",TitelS);
                    intent.putExtra("RohstoffId", rohstoffId);

                    startActivity(intent);
                }
            });
        }



    }
}
