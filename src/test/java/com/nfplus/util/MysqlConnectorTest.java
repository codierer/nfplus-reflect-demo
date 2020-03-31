package com.nfplus.util;

import java.sql.*;

/**
 * @author zac
 * @description: 连接数据库
 * @data 2020 2020/3/8 10:30
 */
public class MysqlConnectorTest {

    private static final String DRIVERCLASS = "com.mysql.cj.jdbc.Driver";
    //useUnicode表示允许自定义编码
    //characterEncoding设置编码格式
    private static final String URL = "jdbc:mysql://localhost:3306/nfplus?useUnicode=true&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void main(String[] args){
        System.out.println("====================test========================");
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName(DRIVERCLASS);
            conn = DriverManager.getConnection(URL, USER, PASS);
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet tableRet = databaseMetaData.getTables(null,"nfplus","student_assess",new String[]{"SCHEMA","TABLE"});
            System.out.println(databaseMetaData.getDatabaseProductName());
            System.out.println(databaseMetaData.getDriverMinorVersion());
            System.out.println(databaseMetaData.getDatabaseMajorVersion());
            System.out.println("数据库结构");
            while (tableRet.next()){
                System.out.print(tableRet.getString("TABLE_NAME")+"\t");
                System.out.println(tableRet.getString("REMARKS"));
            }
            System.out.println("表结构");
            ResultSet columnSet = databaseMetaData.getColumns(null,"nfplus","student_assess","%");
            while (columnSet.next()){
                System.out.print(columnSet.getString("COLUMN_NAME")+"\t");
                System.out.println(columnSet.getString("TYPE_NAME"));
                //System.out.print(columnSet.getString("TABLE_SCHEMA"+"\t"));
                //System.out.print(columnSet.getString("TABLE_SCHEMA"));
            }
            System.out.println("实例化statement对象");
            stmt = conn.createStatement();
//            String sql = "insert into student_assess(school_name,student_name,course_name,score) values ('五羊小学','张三','数学',90)";
//            boolean rs = stmt.execute(sql);
//            System.out.println("插入数据返回的boolean值："+rs);
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
