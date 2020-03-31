package com.nfplus.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;


/**
 * @author zac
 * @description: 基于excel为数据源 导入到数据库
 * @data 2020 2020/3/10 11:22
 */
public class ExcelPOIReaderUtil {


    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";


    /**
     * 读取excel数据，返回数据内容
     * @param fileName 文件路径，及其文件名
     * @return 文件数据格式
     * @throws Exception
     */
    public static List<List<Object>> readExcel(String fileName) throws Exception {
        System.out.println("**************************读取文件**************************");
        File file = new File(fileName);
        if (!file.exists()){
            throw new Exception("the file is not exists ! ! !");
        }
        List<List<Object>> lists = new LinkedList<List<Object>>();
        //获取文件格式，区分xls和xlsx
        String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
        Workbook workbook = null;
        if(fileType.equalsIgnoreCase(XLS)){
            //xls调用poi
            workbook = new HSSFWorkbook(new FileInputStream(file));
        }else if(fileType.equalsIgnoreCase(XLSX)){
            //xlsx调用poi-ooxml
            workbook = new XSSFWorkbook(new FileInputStream(file));
        }
        Sheet sheet = workbook.getSheetAt(0);
        Row row = null;
        Cell cell = null;
        //第二行开始读取数据，第一行是表头
        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++){
            row = sheet.getRow(i);
            if(row==null){
                continue;
            }
            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum() ; j <=(row.getLastCellNum() -1); j++){
                Object value = null;
                cell = row.getCell(j);
                if(cell==null){
                    continue;
                }
                switch (cell.getCellType()){
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        value = cell.getNumericCellValue();
                        break;
                    case BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case FORMULA:
                        value = cell.getCellFormula();
                        break;
                    case BLANK:
                        break;
                    default:
                        value = cell.toString();
                }
                if(value !=null && !value.equals("")){
                    linked.add(value);
                }
            }
            if (linked.size() != 0){
                lists.add(linked);
            }
        }
        return lists;
    }


}
