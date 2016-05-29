package com.example.darkayy.aueraaetas.util;

/**
 * Created by Tom on 29.05.2016.
 */
public class Gebaeude {
    int id, baudauer, produktionsdauer, rohstoff_id_prodziert_gelagert;
    String name;
    String beschreibung;
    String typ;



    int level;


    public Gebaeude(int id, String name, String beschreibung, String typ, int baudauer, int produktionsdauer, int rohstoff_id_prodziert_gelagert) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.typ = typ;
        this.baudauer = baudauer;
        this.produktionsdauer = produktionsdauer;
        this.rohstoff_id_prodziert_gelagert = rohstoff_id_prodziert_gelagert;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBaudauer(int baudauer) {
        this.baudauer = baudauer;
    }

    public void setProduktionsdauer(int produktionsdauer) {
        this.produktionsdauer = produktionsdauer;
    }

    public void setRohstoff_id_prodziert_gelagert(int rohstoff_id_prodziert_gelagert) {
        this.rohstoff_id_prodziert_gelagert = rohstoff_id_prodziert_gelagert;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }


    public int getId() {
        return id;
    }

    public int getBaudauer() {
        return baudauer;
    }

    public int getProduktionsdauer() {
        return produktionsdauer;
    }

    public int getRohstoff_id_prodziert_gelagert() {
        return rohstoff_id_prodziert_gelagert;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getTyp() {
        return typ;
    }
}
