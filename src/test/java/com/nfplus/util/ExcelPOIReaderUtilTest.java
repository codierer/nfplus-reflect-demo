package com.nfplus.util;

/**
 * @author zac
 * @description:
 * @data 2020 2020/3/10 15:34
 */
public class ExcelPOIReaderUtilTest {

    public static void main(String[] args){
        //xlsx 2007
        try {
            ExcelPOIReaderUtil.readExcel("src/main/resources/init-data.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
