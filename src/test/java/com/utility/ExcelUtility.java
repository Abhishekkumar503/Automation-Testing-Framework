package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelUtility {

	public static Iterator<User> readExcelFile(String fileName) {
		User user = null;
		List<User> userList = new ArrayList<User>();
		Cell firstCell;
		Cell secondCell;
		Row row;
		File file = new File(System.getProperty("user.dir") + "/testData/" + fileName + ".xlsx"); // Location
		try {
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file); 
			XSSFSheet xssfSheet = xssfWorkbook.getSheet(""); // pointing to sheet
			Iterator<Row> rowIterator = xssfSheet.iterator(); // iterating row wise 
			rowIterator.next(); // for skip header line 
			
			while(rowIterator.hasNext()) // Checking the row has next
			{
				row = rowIterator.next(); //checking next cell
				firstCell = row.getCell(0); // getting 1 row 1st cell value 
				secondCell = row.getCell(1); // getting 1 row 2nd cell value 
				user = new User(firstCell.toString(), secondCell.toString()); // mapping with pojo
				userList.add(user); // adding to the UserList
				System.out.print(firstCell.toString() + "," +secondCell.toString());
				xssfWorkbook.close();
			}
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return userList.iterator();
	}

}
