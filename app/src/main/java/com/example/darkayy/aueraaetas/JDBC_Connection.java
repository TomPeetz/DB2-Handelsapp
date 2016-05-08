package com.example.darkayy.aueraaetas; /**
 * Created by Tom on 01.05.2016.
 */

import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;


import oracle.jdbc.driver.OracleDriver;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class JDBC_Connection {

    public static void main(String args[]) {
        // -> für DML Tests
        /*
        String[] values = new String[]{"Golderz", "Abgebautes Material, aus dem Gold gewonnen werden kann", "0,5", "1", "0"};
        System.out.println("Anzahl der veränderten Spalten: \t" + doDML(
                "INSERT INTO ROHSTOFFE (Rohstoffname, Rohstoffbezeichnung, Bot_Verkaufspreis, Bot_Einkaufspreis, Startwert)" +
                "VALUES (?, ?, ?, ?, ?)", values)
        );
        */

        // -> für DQL Tests
        /*
        String[] values = new String[]{"1"};
        ArrayList[] ergebnis = doSelect("SELECT * FROM Rohstoffe WHERE Rohstoff_ID = ?", values);
        ArrayList row;
        for (int i = 0; i < ergebnis.length; i++) {
            row = ergebnis[i];
            for(int k = 0; k < row.size(); k++) {
                System.out.print(row.get(k) + "\t");
            }
            System.out.println();
        }
        */
    }

    public static int doDML(String query, String[] values) {
        Connection con = connect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    try {
                        int k = Integer.parseInt(values[i]);
                        ps.setInt(i + 1, k);
                    } catch (NumberFormatException e) {
                        try {
                            Date d = java.sql.Date.valueOf(values[i]);
                            ps.setDate(i + 1, d);
                        } catch (IllegalArgumentException iae) {
                            ps.setString(i + 1, values[i]);
                        }
                    }
                }
            }
            int ergebnis = ps.executeUpdate();
//            con.commit();
            ps.close();
            close(con);
            return ergebnis;
        } catch (SQLException e) {
            e.printStackTrace();
            close(con);
            return -1;
        }
    }

    private static Connection connect() {
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@studidb.gm.fh-koeln.de"+":1521:VLESUNG", "ORA_AI_3_6", "cFHYUtBS7VwR");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return con;
        }
    }

    private static boolean close(Connection con) {
        try {
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList[] doSelect(String query, String[] values) {
        Connection con = connect();
        try {
            PreparedStatement ps = con.prepareStatement(query,  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs;
            ArrayList[] resultList;
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    try {
                        int k = Integer.parseInt(values[i]);
                        ps.setInt(i + 1, k);
                    } catch (NumberFormatException e) {
                        try {
                            Date d = java.sql.Date.valueOf(values[i]);
                            ps.setDate(i + 1, d);
                        } catch (IllegalArgumentException iae) {
                            ps.setString(i + 1, values[i]);
                        }
                    }
                }
            }
            rs = ps.executeQuery();
            int numberOfColumns = rs.getMetaData().getColumnCount();
            int size = 0;
            try {
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
            }
            catch(Exception ex) {
                size = 0;
            }
            resultList = new ArrayList[size];
            int count = 0;
            while(rs.next()) {
                resultList[count] = new ArrayList();
                for (int i = 1; i <= numberOfColumns; i++) {
                    resultList[count].add(rs.getString(i));
                }
                count++;
            }
            ps.close();
            rs.close();
            close(con);
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            close(con);
            return null;
        }
    }






}
