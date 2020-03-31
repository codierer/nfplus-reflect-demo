package com.nfplus.test;

import com.nfplus.util.ExcelPOIReaderUtil;
import com.nfplus.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author zac
 * @description:测试读取excel文件并批量添加数据到数据库
 * @data 2020 2020/3/10 16:19
 */
public class Test4 {

    public static void main(String[] args) throws Exception {
        List<List<Object>> lists = ExcelPOIReaderUtil.readExcel("src/main/resources/init-data.xls");
        Connection conn = JDBCUtil.getConnection();
        Statement stat = conn.createStatement();
        for (int i = 0; i< lists.size();i++){
            String sql = "insert into student_assess(school_name,student_name,course_name,score) values ('"+lists.get(i).get(0)+"','"+lists.get(i).get(1)+"','"+lists.get(i).get(2)+"',"+lists.get(i).get(3)+")";
            if(stat.execute(sql)){
                return;
            }
        }

        JDBCUtil.close(conn,stat);

    }
}
