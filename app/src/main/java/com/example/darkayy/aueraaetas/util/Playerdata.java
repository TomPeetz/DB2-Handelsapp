package com.example.darkayy.aueraaetas.util;

import com.example.darkayy.aueraaetas.webapi.API_Connection;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by Tobias Theus on 13/05/2016.
 */
public class Playerdata {
    private static int id;
    private static String username, email, pwhash, salt, lastLogin, profilePic;
    private static boolean isBanned;

    /**
     * Funktion zum einloggen eines Users.
     * @param username Username
     * @param pw Passwort als Plaintext
     * @return true oder false je nachdem ob Login erfolgreich.
     */
    public static boolean login(String username, String pw){

        API_Connection con = new API_Connection();
        ArrayList<String> params = new ArrayList<String>();
        params.add(API_Connection.APIKEY);
        params.add(username);
        JsonResult result = null;

        try {
            result = con.query(API_Connection.GETSALT, params);
            String[] exp = {"Salt"};
            ArrayList<String> salt = result.parseResult(exp);
            String[] par = {API_Connection.APIKEY, pw, salt.get(0)};
            result = con.query(API_Connection.PWGEN, par);
            String[] exp3 = {"pw", "salt"};
            ArrayList<String> pwhash = result.parseResult(exp3);
            String hash = pwhash.get(0);
            System.out.println("PLAYERDATA: " + username + ", " + pw + ", " + salt.get(0) + ", " + hash );
            params.add(hash);
            result = con.query(API_Connection.LOGIN, params);
            String[] exp2 = {"id", "name", "email", "passw", "salt", "login", "gesperrt", "picture"};
            ArrayList<String> logindaten = result.parseResult(exp2);
            Playerdata.id = Integer.parseInt(logindaten.get(0));
            Playerdata.username = logindaten.get(1);
            Playerdata.email = logindaten.get(2);
            Playerdata.pwhash = logindaten.get(3);
            Playerdata.salt = logindaten.get(4);
            Playerdata.lastLogin = logindaten.get(5);
            Playerdata.isBanned = Boolean.parseBoolean(logindaten.get(6));
            Playerdata.profilePic = logindaten.get(7);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String generateSHA256Hash(String text){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes("UTF-8"));
            byte[] digest = md.digest();
            String pwhash = String.format("%064x", new java.math.BigInteger(1, digest));
            System.out.println("PW GENERATED: " + pwhash);
            return pwhash;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getId() {
        return id;
    }

    public static String getUsername() {
        return username;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPwhash() {
        return pwhash;
    }

    public static String getSalt() {
        return salt;
    }

    public static String getLastLogin() {
        return lastLogin;
    }

    public static String getProfilePic() {
        return profilePic;
    }

    public static boolean isBanned() {
        return isBanned;
    }

}
