package com.guilin.java6.apache_poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by guilin1 on 15/6/24.
 */
public class MyTest {

    @Test
    public void test1() throws IOException {
        Workbook wb = new HSSFWorkbook();
        //Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        sheet.autoSizeColumn(5);

        String[] headers = new String[]{"地区", "区县", "区域编号", "投诉总次数", "历史重复投诉次数", "历史投诉次数(前台拦截)",
                "历史投诉次数(普通)钻石卡", "历史投诉次数(普通)金卡", "历史投诉次数(普通)银卡", "历史投诉次数(普通)普卡",
                "历史投诉次数(升级)10015", "历史投诉次数(升级)总经理", "历史投诉次数(工信部)", "A类得分"};

        PrintSetup ps = sheet.getPrintSetup();

        sheet.setAutobreaks(true);

        ps.setFitHeight((short)1);
        ps.setFitWidth((short)1);

        // Create a row and put some cells in it. Rows are 0 based.
        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("yyyy/MM/dd hh:mm:ss"));


        Row row = sheet.createRow((short) 0);
        for (int i = 0; i < headers.length; i++) {

            if(i==3){
                Cell cell = row.createCell(i);
                cell.setCellValue(new Date());
                cell.setCellStyle(cellStyle);
            }else if(i==4){
                row.createCell(i).setCellValue(true);
            }else{
                row.createCell(i).setCellValue(headers[i]);
            }
        }


//        CreationHelper createHelper = wb.getCreationHelper();
//        row.createCell(2).setCellValue(
//                createHelper.createRichTextString("This is a string"));
//        row.createCell(3).setCellValue(true);

        // Write the output to a file
        FileOutputStream fileOut = getFileOutputStream(wb);
        fileOut.close();
    }

    private static FileOutputStream getFileOutputStream(Workbook wb) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("/Users/guilin1/Downloads/workbook.xls");
        wb.write(fileOut);
        return fileOut;
    }

}
