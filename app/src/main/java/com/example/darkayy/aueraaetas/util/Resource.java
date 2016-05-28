package com.example.darkayy.aueraaetas.util;

/**
 * Created by Darkayy on 27/05/2016.
 */
public class Resource {
    private int id, menge;
    private String name;

    public Resource(int id, String name, int menge){
        this.id = id;
        this.name = name;
        this.menge = menge;
    }

    public int getId() {
        return id;
    }

    public int getMenge() {
        return menge;
    }

    public String getName() {
        return name;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }
}
