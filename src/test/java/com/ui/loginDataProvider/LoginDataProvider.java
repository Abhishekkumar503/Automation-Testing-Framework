package com.ui.loginDataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelUtility;

public class LoginDataProvider {

	@DataProvider(name = "loginDataProvider")
	public Iterator<Object[]> loginDataProvider() {

		Gson gson = new Gson();

		File jsonFile = new File(System.getProperty("user.dir") + "/testData/loginData.json");

		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		TestData testData = gson.fromJson(fileReader, TestData.class);

		List<Object[]> list = new ArrayList<Object[]>();

		for (User user : testData.getData()) {
			list.add(new Object[] { user });
		}

		return list.iterator();
	}
	
	@DataProvider(name = "csvDataProvider")
	public Iterator<User> csvLoginDataProvider()
	{
		return CSVReaderUtility.readCSVFile("testData");
	}
	
	@DataProvider(name = "excelDataProvider")
	public Iterator<User> excelLoginDataProvider()
	{
		return ExcelUtility.readExcelFile("testData");
	}
}
