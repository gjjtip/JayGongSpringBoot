package com.example.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {


    private static String hostName = "jaygong.mysql.database.azure.com";
    private static String dbName = "jaygong";
    private static String user = "jaygong@jaygong";
    private static String password = "Password3#";
    private static String portNumber = "3306";
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            String url ="jdbc:mysql://jaygong.mysql.database.azure.com:3306/sys?verifyServerCertificate=true&useSSL=true&requireSSL=false&serverTimezone=UTC";
            conn = DriverManager.getConnection(url, "jaygong@jaygong", password);
//            conn = DriverManager.getConnection(url);
            System.out.println(conn.getCatalog());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("access success");
    }

}
