/**
 * Created by Tom on 01.05.2016.
 */

import java.sql.DriverManager;
import java.sql.*;


import oracle.jdbc.driver.OracleDriver;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class JDBC_Connection {
    public static void main(String args[]) {
        //DriverManager.getConnection('jdbc:oracle:thin:testuser/test@localhost');
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@studidb.gm.fh-koeln.de"+":1521:VLESUNG", "ORA_AI_3_6", "cFHYUtBS7VwR");
            //con = DriverManager.getConnection("jdbc:oracle:thin:testuser/testpass@localhost");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT CHARAKTERNAME FROM SPIELER");
            while(rs.next()) {
                //System.out.println(rs.getString(1) + "\t");
                System.out.println(rs.getType());
                //System.out.print(rs.getString(2));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
