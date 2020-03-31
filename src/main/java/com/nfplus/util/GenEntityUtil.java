package com.nfplus.util;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

/**
 * @author zac
 * @description: 使用Freemarker，自动生成对应数据库的数据表的实体类
 * @data 2020 2020/3/9 10:31
 */
public class GenEntityUtil {

    private static String fltpath = "src/main/java/com/nfplus//ftl";

    private static String classpath = "src/main/java/com/nfplus/m/";

    public static void genEntity(String database, String table){

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        try {
            configuration.setDirectoryForTemplateLoading(new File(fltpath));
            //获取数据库及表结构
            Map<String, Map<String, Struct>> dataMap = FormatDTSUtil.getDTS(database, table);
            //引入模版
            Template template = configuration.getTemplate("Entity.ftl");
            File entityFile = new File(classpath + dataMap.get("table").get("col0").getClassName()+".java");
            //生成文档
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(entityFile)));
            //System.out.println(dataMap.get("table").get("col0").getClassName());
            template.process(dataMap, writer);
            System.out.println("**************************生成实体类**************************");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e){
            e.printStackTrace();
        }
    }


}