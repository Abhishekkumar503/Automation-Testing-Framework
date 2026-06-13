package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtility {
	
	public static Iterator<User> readCSVFile(String fileName)
	{
		File file = new File(System.getProperty("user.dir") + "/testData/"+ fileName + ".csv");
		
		FileReader reader = null;
		CSVReader csvReader;
		String[] line;
		User user;
		List<User> userList = null;
		try {
			reader = new FileReader(file);
			csvReader = new CSVReader(reader);
			line = csvReader.readNext(); // readNext return Array of String of correct location like At Line number1
			
			userList = new ArrayList<>();
			
			while((line = csvReader.readNext()) != null)
			{
				user = new User(line[0],line[1]);
				System.out.println(Arrays.toString(line));
				userList.add(user);
			}
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
		return userList.iterator();
		
	}

}
