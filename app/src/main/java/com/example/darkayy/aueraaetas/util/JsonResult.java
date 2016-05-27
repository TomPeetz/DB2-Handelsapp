package com.example.darkayy.aueraaetas.util;

import java.util.ArrayList;

public class JsonResult {
    private int size = 0;
    private ArrayList<String> namen = new ArrayList<String>();
    private ArrayList<String> data = new ArrayList<String>();

    public void add(String namen, String data){
        this.namen.add(namen);
        this.data.add(data);
        size++;
    }

    public ArrayList<String> parseResult(String[] name){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < name.length; i++){
            result.add(getData(name[i]));
        }
        for(int i = 0; i < name.length; i++){
            namen.remove(0);
            data.remove(0);
        }
        size = size - name.length;
        return result;
    }


    public String getData(String name){
        String s = searchSpalte(name);
        for(int i = 0; i < namen.size(); i++){
            if(namen.get(i).equals(s)){
                return data.get(i);
            }
        }
        return "";
    }

    public String searchSpalte(String name){
        for(int i = 0; i < namen.size(); i++){
            if(namen.get(i).contains(name)){
                return namen.get(i);
            }
        }
        return "";
    }

    public String getData(int n){
        if(n < size()){
            return data.get(n);
        }
        return "";
    }
    public String getSpalte(int n){
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
}
