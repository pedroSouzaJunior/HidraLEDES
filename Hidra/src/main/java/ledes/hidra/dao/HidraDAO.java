/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.dao;

import java.sql.*;

/**
 *
 * @author danielli
 */
public class HidraDAO {

    private final String localPath;

    public HidraDAO(String localPath) {
        super();
        this.localPath = localPath;

    }

    public boolean connection() {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + localPath + "hidra.db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS ASSET "
                    + "(ID CHAR(50) NOT NULL,"
                    + " NAME CHAR(50) UNIQUE    NOT NULL, "
                    + " PRIMARY KEY (ID,NAME ))";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return false;
    }

    public boolean insertion(String name, String id) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + localPath + "hidra.db");
            c.setAutoCommit(false);
          //  System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO ASSET (ID,NAME) "
                    + "VALUES (" + id + "," + "'"+ name+"'" + "  );";
            System.out.println(sql);
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
      
        return false;
    }

    public boolean find(String name, String id) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + localPath + "hidra.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            boolean ret;
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM ASSET "
                    + "WHERE ID = "+ id+" AND NAME = "+"'"+ name+"'"+ ";")) {
                ret = rs.isBeforeFirst();
            }
            stmt.close();
            c.close();
          
            return ret;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        
        return false;
    }

}

