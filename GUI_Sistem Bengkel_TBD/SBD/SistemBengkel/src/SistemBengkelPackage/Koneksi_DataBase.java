/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemBengkelPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Windows10
 */
public class Koneksi_DataBase {

    Connection conn;
    Statement stat;
    String nameDB = "DBlogin";

    public Connection Bengkel_Connection() throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=" + nameDB + ";integrateSecurity=True"; //url database
            String user = "rinroya"; //user database
            String pass = "rinroya"; //password database
            conn = DriverManager.getConnection(url, user, pass);
            stat = conn.createStatement();

        } catch (SQLException e) {
            System.err.println("Koneksi Gagal " + e.getMessage()); //perintah menampilkan error pada koneksi
        }

        return conn;
    }
}
