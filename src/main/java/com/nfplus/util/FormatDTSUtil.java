package com.nfplus.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zac
 * @description: 格式化数据库和表的结构，返回Map 类型数据集
 * @data 2020 2020/3/9 15:04
 */
public class FormatDTSUtil {

    /**
     * 获取数据库及其表结构
     * @return
     */
    public static Map<String ,Map<String, Struct>> getDTS(String database, String table) {
        System.out.println("**************************获取数据库结构**************************");
        Map<String ,Map<String, Struct>> dts = new HashMap();
        Connection conn = null;
        Statement stat = null;
        try {
            conn = JDBCUtil.getConnection();
            stat = conn.createStatement();
            String sql = "select table_name,column_name,column_comment,column_type,column_key,column_default from INFORMATION_SCHEMA.Columns where table_schema='"+database+"'"+" and table_name='"+table+"'";
            ResultSet rs  = stat.executeQuery(sql);
            Map<String, Struct> col = new HashMap<String, Struct>();
            int i = 0;
            while (rs.next()){
                String className = formatUpCase(rs.getString("table_name"));
                String columnName = formatUpCase(rs.getString("column_name"));
                String columnNameForVar = formatVarName(rs.getString("column_name"));
                String type = formatType(rs.getString("column_type"));
                Struct sub = new Struct.Build()
                        .className(className)
                        .columnName(columnName)
                        .columnNameForVar(columnNameForVar)
                        .type(type)
                        .comment(rs.getString("column_comment"))
                        .buildParams();
                col.put("col"+i, sub);
                i++;
            }
            dts.put("table",col);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,stat);
        }
        return dts;
    }

    /**
     * 格式化单峰名，字符串首字母小写且后续单词首字母大写
     * @param column
     * @return
     */
    public static String formatVarName(String column) {
        char c[] = column.toCharArray();
        //将'——'后的字母转大写
        int m = 0;
        //System.out.println(c.length);
        for(int i=0;i<c.length;i++){
            if(c[i]=='_'){
                m = i+1;
                if (c[m]<=122 && 97<=c[m]) {
                    c[m] -= 32;
                }
            }
        }
        return String.valueOf(c).replaceAll("_","");
    }

    /**
     * 将数据库类型转换为java对应的数据类型
     * @param type
     * @return
     */
    public static String formatType(String type) {
        if (type.contains("char")){
            return "String";
        }else if(type.contains("int")){
            return "int";
        }
        return "there are no matches for this type ！！";
    }

    public static String formatTableName(String tableName){
        return null;
    }

    /**
     * 首字母转大写
     * @param str
     * @return
     */
    public static String formatUpCase(String str){
        char c[] = str.toCharArray();
        //满足小写字母 则转为大写
        if (c[0]<=122 && 97<=c[0]) {
            c[0] -= 32;
        }
        //将'——'后的字母转大写
        int m = 0;
        //System.out.println(c.length);
        for(int i=0;i<c.length;i++){
            if(c[i]=='_'){
                m = i+1;
                if (c[m]<=122 && 97<=c[m]) {
                    c[m] -= 32;
                }
            }
        }
        return String.valueOf(c).replaceAll("_","");
    }

}
