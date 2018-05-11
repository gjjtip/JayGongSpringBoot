package com.example.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
public class HelloController {

//    private static String hostName = "jaygongsqlserver.database.windows.net";
//    private static String dbName = "jaygong";
//    private static String user = "jaygong";
//    private static String password = "Password3#";
//    private static String portNumber = "3306";
//
//    @RequestMapping("/hello")
//    public String index() throws SQLException {
////        String url ="jdbc:mysql://"+hostName+":"+portNumber+"/"+dbName+"?user="+user+"&password="+password+"&useSSL=true";
//        Connection conn = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e1) {
//            e1.printStackTrace();
//        }
//        try {
//            String url = "jdbc:sqlserver://jaygongsqlserver.database.windows.net:1433;database=jaygong;user=jaygong@jaygongsqlserver;password=Password3#;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
//            conn = DriverManager.getConnection(url);
//
//        } catch (SQLException e) {
//            System.out.println("error!!!!");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            return e.getMessage();
//        }
//        return conn.getCatalog();
//    }
//
//    @RequestMapping("/test")
//    public String test() throws SQLException {
//        return "Just for test!";
//    }

    @RequestMapping("/getip")
    public String getIp(HttpServletRequest request) {
        return getIpAddr(request);
    }


    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {

                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }

            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }
}

