/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe referente ao controle das operações do banco de dados HidraDAO.
 *
 * @author danielli
 */
public class HidraDAO {

    private final String localPath;

    public HidraDAO(String localPath) {
        super();
        this.localPath = localPath;

    }

    /**
     * Método responsável por efetuar a conexão com o banco de dados. Caso a
     * tabela que representa um ativo ainda não exista ela será criada. Um ativo
     * é representado por um nome e um id.
     *
     * @return true caso a operação ocorra com sucesso, false caso contrário.
     */
    public boolean connection() {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + localPath + "hidra.db");
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

    /**
     * Método responsável pela inserção de um novo ativo no banco de dados.
     * Recebe informações referentes ao nome e ao id de um ativo e o armazena no
     * banco de dados.
     *
     * @param name - informação referente ao nome do ativo.
     * @param id - informação referente ao id do ativo.
     * @return
     */
    public boolean insertion(String name, String id) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + localPath + "hidra.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO ASSET (ID,NAME) "
                    + "VALUES (" + id + "," + "'" + name + "'" + "  );";

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

    /**
     * Método responsável por efetuar buscas no banco de dados. Recebe
     * informações referentes ao nome e ao id de um ativo.
     *
     * @param name - nome do ativo
     * @param id - id do ativo
     * @return true caso o ativo esteja presente no banco de dados do
     * repsitório, false caso contrário.
     */
    public boolean find(String name, String id) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + localPath + "hidra.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            boolean ret;
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM ASSET "
                    + "WHERE ID = " + id + " AND NAME = " + "'" + name + "'" + ";")) {
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

    /**
     * Método responsável por efetuar buscas no banco de dados. Recebe
     * informações referentes ao nome de um ativo.
     *
     * @param name - nome do ativo
     * @return true caso o ativo esteja presente no banco de dados do
     * repsitório, false caso contrário.
     */
    public boolean find(String name) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + localPath + "hidra.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            boolean ret;
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM ASSET "
                    + "WHERE  NAME = " + "'" + name + "'" + ";")) {
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

    /**
     * Método responsável por excluir as informações de um ativo armazenado no
     * banco de dados.
     *
     * Recebe informações referentes ao nome e ao id de um ativo.
     *
     * @param name - nome do ativo.
     * @param id - id do ativo.
     * @return true caso operação ocorra com sucesso, false caso contrário.
     */
    public boolean delete(String name, String id) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + localPath + "hidra.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "DELETE FROM ASSET "
                    + "WHERE ID = " + id + " AND NAME = " + "'" + name + "'" + ";";
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return false;
    }

    /**
     * Método responsável por listar todos os ativos representados no banco de
     * dados.
     *
     * @return Conjunto de ativos.
     */
    public Map<String, String> selectAll() {

        Map<String, String> assetList = new HashMap<>();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + localPath + "hidra.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ASSET;");
            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("NAME");

                assetList.put(id, name);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return assetList;
    }

}
