package com.nfplus.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author zac
 * @description:
 * @data 2020 2020/3/9 15:23
 */
public class JDBCUtilTest {

    public static void main(String[] args){

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stat = conn.createStatement();

            String sql = "select * from student_assess";
            rs = stat.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString("school_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBCUtil.close(conn,stat,rs);
    }
}
