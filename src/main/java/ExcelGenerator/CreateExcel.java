package ExcelGenerator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class CreateExcel {
    public static void main(String[] args) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("login");

            // Header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("email");
            headerRow.createCell(1).setCellValue("password");

            // Data row
            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue("senax57658@sixoplus.com");
            dataRow.createCell(1).setCellValue("9876543210");

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            FileOutputStream out = new FileOutputStream("login.xlsx");
            workbook.write(out);
            out.close();
            workbook.close();

            System.out.println("login.xlsx created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}