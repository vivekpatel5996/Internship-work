/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.eproctortoars;

import java.sql.*;
public class ARSDatabaseHandler {
    public Connection connection;
    public Statement stmt;
    public ResultSet rs;
    public String user = "postgres";
    public String pass = "argusadmin";
    public String url = "jdbc:postgresql://192.1.200.59:5432/arsTest";
    public String driver = "org.postgresql.Driver";
    public ARSDatabaseHandler() {
    }
    public Connection makeconnection() throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }
    public void releaseConnection() throws Exception {
        connection.close();

    }
    public ResultSet executeQuery(String query) throws Exception {

        rs = null;
        stmt = connection.createStatement();
        rs = stmt.executeQuery(query);
        return rs;
    }
    public int executeUpdate(String query) throws Exception {
        int rowaffected = 0;
        stmt = connection.createStatement();
        rowaffected = stmt.executeUpdate(query);
        return rowaffected;
    }
}
