package com.example.darkayy.aueraaetas.webapi;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Tobias Theus on 18/05/2016.
 */

public class API_Connection {
    public static final String APIKEY           = "5eaf0d52648bade4c52b50aa4fe8eb7e95b366f04207cc5f43be90049ec59de0";
    public static final String LOGIN 		    = "login.php/{?}/{?}/{?}";
    public static final String GETSALT 		    = "login.php/{?}/{?}";
    public static final String REGISTER         = "player.php/new/{?}/{?}/{?}/{?}/{?}";
    public static final String GETRESOURCES     = "lager.php/resources/{?}/{?}";
    public static final String GETMENGEN        = "lager.php/amount/{?}/{?}";

    public ArrayList<String> query(final String TYPE, ArrayList<String> params) throws API_Exception{
        if(checkParams(TYPE,params)){
            String s  = TYPE;


            for(String x: params){
                s = s.replaceFirst("\\{\\?\\}", x);
            }
            API_Query query = new API_Query();
            query.prepareStatement(s);

            FutureTask<ArrayList<String>> futureTask1 = new FutureTask<ArrayList<String>>(query);
            ExecutorService executor = Executors.newFixedThreadPool(1);
            executor.execute(futureTask1);
            try {
                ArrayList<String> result = futureTask1.get();
                if(result.size() == 0)
                    throw new API_Exception("Resultsize empty");
                return result;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new Exception("Parameter Liste falsch!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<String>();
    }

    public ArrayList<String> query(final String TYPE, String[] params){
        ArrayList<String> buff = new ArrayList<String>();
        for(int i = 0; i < params.length; i++){
            buff.add(params[i]);
        }
        ArrayList<String> result = null;
        try {
            result = query(TYPE, buff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean checkParams(final String TYPE, ArrayList<String> params){
        char[] x = TYPE.toCharArray();
        int count = 0;
        for(int i = 0; i < x.length; i++){
            if(x[i] == '?')
                count++;
        }
        boolean result = (count == params.size()) ? true : false;
        System.out.println("Counter: " + count + ", Status = " + result);
        return result;
    }
}
