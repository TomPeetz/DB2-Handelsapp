package com.example.darkayy.aueraaetas.util;

import java.util.ArrayList;

public class JsonResult {
    private int size = 0;
    private ArrayList<String> namen = new ArrayList<String>();
    private ArrayList<String> data = new ArrayList<String>();

    /**
     * Fügt einen Ergebnissatz zur Ergebnismenge hinzu. Nur nützlich für die Readerklasse.
     * @param namen Spaltenname
     * @param data Ergebnis
     */
    public void add(String namen, String data){
        this.namen.add(namen);
        this.data.add(data);
        size++;
    }

    /**
     * Parsed eine Ergebnismenge aus dem kompletten Ergebnissatz und gibt dieses als ArrayList wieder.
     * Um die komplette Ergebnismenge der Abfrage zu kriegen:
     * while(!result.isEmpty()) und dann parsen!
     * @param name Array in dem die Erwarteten Spaltennamen stehen. Bsp: {"id", "name", "menge"} für Rohstoffe
     * @return Ergebnismenge der Abfrage
     */
    public ArrayList<String> parseResult(String[] name){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < name.length; i++){
            result.add(getData(name[i].toLowerCase()));
        }
        for(int i = 0; i < name.length; i++){
            namen.remove(0);
            data.remove(0);
        }
        size = size - name.length;
        return result;
    }


    private String getData(String name){
        String s = searchSpalte(name);
        for(int i = 0; i < namen.size(); i++){
            if(namen.get(i).equals(s)){
                return data.get(i);
            }
        }
        return "";
    }

    /**
     * Sucht eine Spalte in der Ergebnismenge
     * @param name Gesuchter Spaltenname in der Ergebnismenge
     * @return Genauer Spaltenname der gesucht wurde. Leer wenn nicht gefunden!
     */
    public String searchSpalte(String name){
        for(int i = 0; i < namen.size(); i++){
            if(namen.get(i).contains(name)){
                return namen.get(i);
            }
        }
        return "";
    }

    private String getData(int n){
        if(n < size()){
            return data.get(n);
        }
        return "";
    }
    private String getSpalte(int n){
        if(n < size()){
            return namen.get(n);
        }
        return "";
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Gibt kompletten Ergebnissatz in der Console aus.
     */
    public void printResult(){
        for(int i = 0; i < size(); i++){
            System.out.println(namen.get(i) + ": " + data.get(i));
        }
    }
}
