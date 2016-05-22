package com.example.darkayy.aueraaetas.util;

/**
 * Created by Tobias Theus on 13/05/2016.
 */
public class Playerdata {
    private int id;
    private String username, email, pwhash, salt, lastLogin, profilePic;
    private boolean isBanned;
    public Playerdata(String id, String username, String email, String pwhash, String salt, String lastLogin, String isBanned, String profilePic){
        this.id = Integer.parseInt(id);
        this.username = username;
        this.email = email;
        this.pwhash = pwhash;
        this.salt = salt;
        this.lastLogin = lastLogin;
        this.profilePic = profilePic;
        this.isBanned = Boolean.parseBoolean(isBanned);
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPwhash() {
        return pwhash;
    }

    public String getSalt() {
        return salt;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public boolean isBanned() {
        return isBanned;
    }
}
