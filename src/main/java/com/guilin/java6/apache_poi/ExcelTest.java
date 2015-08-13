package com.guilin.java6.apache_poi;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guilin
 * Date: 14-4-2
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class ExcelTest {

    /**
     * Microsoft Office EXCEL 2007文档的扩展名.xlsx   是Office2007使用的，是用新的基于XML的压缩文件格式取代了
     * 其目前专有的默认文件格式，在传统的文件名扩展名后面添加了字母x（即.docx取代.doc、.xlsx取代.xls，等等），
     * 使其占用空间更小。所以xls所有OFFICE程序都能打开,但.xlsx貌似只有OFFICE2007或者更新的版本才能打开。
     *
     * @throws Exception
     */
    @Test
    public void testNewWorkbook() throws Exception {
        Workbook wb1 = new HSSFWorkbook();
        File file1 = new File(createFileName("testNewWorkbook", "xls"));
        System.out.println("File:" + file1.getAbsolutePath());
        FileOutputStream os1 = new FileOutputStream(file1);
        wb1.write(os1);
        os1.close();

        Workbook wb2 = new XSSFWorkbook();
        File file2 = new File(createFileName("testNewWorkbook", "xlsx"));
        System.out.println("File:" + file2.getAbsolutePath());
        FileOutputStream os2 = new FileOutputStream(file2);
        wb2.write(os2);
        os2.close();
    }

    @Test
    public void testNewSheet() throws Exception {
        Workbook wb = new HSSFWorkbook();  // or new XSSFWorkbook();
        wb.createSheet("1月份");
        wb.createSheet("2月份");

        // Note that sheet name is Excel must not exceed 31 characters
        // and must not contain any of the any of the following characters:
        // 0x0000
        // 0x0003
        // colon (:)
        // backslash (\)
        // asterisk (*)
        // question mark (?)
        // forward slash (/)
        // opening square bracket ([)
        // closing square bracket (])

        // You can use org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
        // for a safe way to create valid names, this utility replaces invalid characters with a space (' ')
        String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "
        wb.createSheet(safeName);

        FileOutputStream fileOut = new FileOutputStream(createFileName("testNewSheet", "xls"));
        wb.write(fileOut);
        fileOut.close();
    }

    @Test
    public void createCell() throws Exception {
        Workbook wb = new HSSFWorkbook();
        //Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow((short) 0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        CreationHelper createHelper = wb.getCreationHelper();
        row.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        // Write the output to a file
        FileOutputStream fileOut = getFileOutputStream(wb);
        fileOut.close();
    }

    @Test
    public void write() throws Exception {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        FileOutputStream fileOut = new FileOutputStream("/Users/guilin1/Downloads/workbook.xls");

        List<String> list = new ArrayList(10000);
        System.out.println(list.size());
        for(int i=0;i<10000;i++){
            list.add("helloadddddddffffffffffffffa dafsdfd"+i);
        }

        long start = System.currentTimeMillis();
        Row row;
        for (int i = 0; i < 10000; i++) {
            row = sheet.createRow((short) i);

            for(int j=0;j<100;j++){
//                row.createCell(j).setCellValue((i+1)+","+(j+1));
                row.createCell(j).setCellValue(list.get(i));
            }

//            if((i+1)%100==0){
//                wb.write(fileOut);
//            }
//            if(i>2000){
//                list = null;
//                break;
//            }
        }
        long s=System.currentTimeMillis();
        wb.write(fileOut);
        System.out.println(System.currentTimeMillis()-s);
        fileOut.close();
        System.out.println(System.currentTimeMillis()-start);
    }

    @Test
    public void testCreateDateCells() throws Exception {
        Workbook wb = new HSSFWorkbook();
        //Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);

        // Create a cell and put a date value in it.  The first cell is not styled
        // as a date.
        Cell cell = row.createCell(0);
        cell.setCellValue(new Date());

        // we style the second cell as a date (and time).  It is important to
        // create a new cell style from the workbook otherwise you can end up
        // modifying the built in style and effecting not only this cell but other cells.
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);

        //you can also set date as java.util.Calendar
        cell = row.createCell(2);
        CellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setDataFormat(createHelper.createDataFormat().getFormat("日期(m/d/yy) 时间：h:mm"));
        cell.setCellStyle(cellStyle2);
        cell.setCellValue(Calendar.getInstance());

        // Write the output to a file
        FileOutputStream fileOut = getFileOutputStream(wb);
        fileOut.close();
    }

    /**
     * Working with different types of cells
     *
     * @throws Exception
     */
    @Test
    public void testCells() throws Exception {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Row row = sheet.createRow(2);
        row.createCell(0).setCellValue(1.1);
        row.createCell(1).setCellValue(new Date());
        row.createCell(2).setCellValue(Calendar.getInstance());
        row.createCell(3).setCellValue("a string");
        row.createCell(4).setCellValue(true);
        row.createCell(5).setCellType(Cell.CELL_TYPE_ERROR);

        Cell cell = row.createCell(6);
        CellStyle cellStyle = wb.createCellStyle();
        //Alignment
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        cell.setCellStyle(cellStyle);
        //Border
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLUE.getIndex());
        cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        cell.setCellValue("Hello World!");

        // Write the output to a file
        FileOutputStream fileOut = getFileOutputStream(wb);
        fileOut.close();
    }

    @Test
    public void testFilesVSInputStream() throws Exception {
        // If using WorkbookFactory, it's very easy to use one or the other:
        // Use a file
//        Workbook wb = WorkbookFactory.create(new File("temp/MyExcel.xls"));

        // Use an InputStream, needs more memory
//        Workbook wb2 = WorkbookFactory.create(new FileInputStream("temp/MyExcel.xlsx"));

        //If using HSSFWorkbook or XSSFWorkbook directly, you should generally go through NPOIFSFileSystem or OPCPackage,
        // to have full control of the lifecycle (including closing the file when done):

        // HSSFWorkbook, File
        //TODO
//        NPOIFSFileSytem fs = new NPOIFSFileSystem(new File("file.xls"));
//        HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot());
//        //....
//        fs.close();
//
//        // HSSFWorkbook, InputStream, needs more memory
//        NPOIFSFileSytem fs = new NPOIFSFileSystem(myInputStream);
//        HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot());
//
//        // XSSFWorkbook, File
//        OPCPackage pkg = OPCPackage.open(new File("file.xlsx"));
//        XSSFWorkbook wb = new XSSFWorkbook(pkg);
//        //....
//        pkg.close();
//
//        // XSSFWorkbook, InputStream, needs more memory
//        OPCPackage pkg = OPCPackage.open(myInputStream);
//        XSSFWorkbook wb = new XSSFWorkbook(pkg);
//        //....
//        pkg.close();

        //TODO
        //List->Excel  写
        //Excel->List 读


    }

    @Test
    public void testGetCellContents() throws Exception {
        File file = new File("res/SYS_PROJECT.xls");
//        File file = new File("res/谷玉泽-201306.xlsx");
        if (file == null) {
            return;
        }
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        Workbook wb = WorkbookFactory.create(file);

//        is.close();
        Sheet sheet1 = wb.getSheetAt(0);
        System.out.println(sheet1.getLastRowNum());

        CellStyle cellStyle = sheet1.getRow(2).getCell(1).getCellStyle();
//        System.out.println(cellStyle);
//        for (Row row : sheet1) {
//            for (Cell cell : row) {
//                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
//                System.out.print(cellRef.formatAsString());
//                System.out.print(" - ");
//
//                switch (cell.getCellType()) {
//                    case Cell.CELL_TYPE_STRING:
//                        System.out.println(cell.getRichStringCellValue().getString());
//                        break;
//                    case Cell.CELL_TYPE_NUMERIC:
//                        if (DateUtil.isCellDateFormatted(cell)) {
//                            System.out.println(cell.getDateCellValue());
//                        } else {
//                            System.out.println(cell.getNumericCellValue());
//                        }
//                        break;
//                    case Cell.CELL_TYPE_BOOLEAN:
//                        System.out.println(cell.getBooleanCellValue());
//                        break;
//                    case Cell.CELL_TYPE_FORMULA:
//                        System.out.println(cell.getCellFormula());
//                        break;
//                    default:
//                        System.out.println(cell.toString());
//                }
//            }
//        }
    }

    @Test
    public void testMergingCells() throws Exception {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow((short) 1);
        Cell cell = row.createCell((short) 1);
        cell.setCellValue("This is a test of merging");

        sheet.addMergedRegion(new CellRangeAddress(
                1, //first row (0-based)
                3, //last row  (0-based)
                1, //first column (0-based)
                2  //last column  (0-based)
        ));

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("/Users/guilin1/Downloads/workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    @Test
    public void testFilesAndColors() throws Exception {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow((short) 1);

        // Aqua background
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(CellStyle.BIG_SPOTS);
        Cell cell = row.createCell((short) 1);
        cell.setCellValue("X");
        cell.setCellStyle(style);

        // Orange "foreground", foreground being the fill foreground not the font color.
        style = wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());

        XSSFFont font = (XSSFFont) wb.createFont();
//        font.setColor(XSSFFont.COLOR_RED);
//        style.setFont(font);

//        HSSFFont font2 = (HSSFFont) wb.createFont();
//        font2.setColor(HSSFFont.COLOR_RED);
//        style.setFont(font2);

        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cell = row.createCell((short) 2);
        cell.setCellValue("X");
        cell.setCellStyle(style);

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("/Users/guilin1/Downloads/workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    @Test
    public void testHeaderAndFooter() throws Exception {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet1 = wb.createSheet("new sheet");
        Sheet sheet2 = wb.createSheet("Sheet2");

//        Header header = sheet.getHeader();
//        header.setCenter("Center Header");
//        header.setLeft("Left Header");
//        header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") +
//                HSSFHeader.fontSize((short) 16) + "Right w/ Stencil-Normal Italic font and size 16");

        sheet2.setSelected(true);
        sheet1.setZoom(6, 4);
        // Create various cells and rows for spreadsheet.
        // Set the rows to repeat from row 4 to 5 on the first sheet.
        sheet1.setRepeatingRows(CellRangeAddress.valueOf("4:5"));
        // Set the columns to repeat from column A to C on the second sheet
        sheet2.setRepeatingColumns(CellRangeAddress.valueOf("A:C"));

        // Shift rows 6 - 11 on the spreadsheet to the top (rows 0 - 5)
        sheet1.shiftRows(5, 10, -5);

        FileOutputStream fileOut = new FileOutputStream("/Users/guilin1/Downloads/workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    private static FileOutputStream getFileOutputStream(Workbook wb) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("/Users/guilin1/Downloads/workbook.xls");
        wb.write(fileOut);
        return fileOut;
    }

    private static String createFileName(String fileName, String fileType) {
        StringBuffer result = new StringBuffer();
        result.append("temp/").append(ExcelTest.class.getSimpleName()).append("_");
        result.append(fileName).append(".").append(fileType);
        return result.toString();
    }
}
