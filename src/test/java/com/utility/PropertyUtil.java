package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constant.Env;

public class PropertyUtil {
	public static String readProperty(Env env, String propertyName) {
		// geting properties location
		System.out.println(System.getProperty("user.dir"));
		File propFile = new File(System.getProperty("user.dir") + "/config/" + env + ".properties");

		// creating file reader to read properteis file
		FileReader fileReader;
		Properties properties = new Properties(); // initailing the properties class
		try {
			fileReader = new FileReader(propFile);
			properties.load(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = properties.getProperty(propertyName); // fetching the values
		System.out.println(value);
		return value;
	}
}
