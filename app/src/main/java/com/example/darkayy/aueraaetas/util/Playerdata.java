package com.example.darkayy.aueraaetas.util;

/**
 * Created by Tobias Theus on 13/05/2016.
 */
public class Playerdata {
    private static int id;
    private static String username, email, pwhash, salt, lastLogin, profilePic;
    private static boolean isBanned;
    private static boolean isValid = false;
    public static void createPlayerdata(String id, String username, String email, String pwhash, String salt, String lastLogin, String isBanned, String profilePic){
        Playerdata.id = Integer.parseInt(id);
        Playerdata.username = username;
        Playerdata.email = email;
        Playerdata.pwhash = pwhash;
        Playerdata.salt = salt;
        Playerdata.lastLogin = lastLogin;
        Playerdata.profilePic = profilePic;
        Playerdata.isBanned = Boolean.parseBoolean(isBanned);
        System.out.println("Spieler: " + username + "(" + id +")eingeloggt! E-Mail: " + email + ", Gebannt: " + isBanned);
        Playerdata.isValid = true;
    }

    public static int getId() {
        return id;
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

    public static boolean isValid() {
        return isValid;
    }
}
