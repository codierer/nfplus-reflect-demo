package com.nfplus.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.sql.*;
import java.util.Properties;

/**
 * @author zac
 * @description: jdbc 封装连接和断开，使用getConnection 建立连接，close 断开连接
 * @data 2020 2020/3/9 11:11
 */
public class JDBCUtil {

    private static String url;
    private static String user;
    private static String password;


    static {
        //加载连接配置参数
        Properties prop = new Properties();
        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driverClassName = prop.getProperty("driverClassName");
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
    }

    /**
     * 建立jdbc连接
     * @return
     */
    public static Connection getConnection(){
        System.out.println("**************************连接数据库**************************");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 断开连接
     * @param conn
     * @param stat
     */
    public static void close(Connection conn, Statement stat){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("**************************断开数据库**************************");

    }

    /**
     * 断开连接重载方法
     * @param conn
     * @param stat
     * @param resultSet
     */
    public static void close(Connection conn, Statement stat, ResultSet resultSet){
        close(conn, stat);
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
