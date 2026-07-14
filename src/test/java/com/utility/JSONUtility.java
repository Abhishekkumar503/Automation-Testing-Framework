package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.constant.Env;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;


public class JSONUtility {

		public static Environment readUrl(Env env) {

		// Initializing the GSON
		Gson gson = new Gson();

		// fetching the file path
		File jsonFile = new File(System.getProperty("user.dir") + "/config/config.json");

		System.out.println(System.getProperty("user.dir") + "/config/config.json");
		// Reading the files

		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Reading the file throw Config class. This is mapping the file to class as well
		Config config = gson.fromJson(fileReader, Config.class);

		// fetching the environment form Config file throw environment getter		
		Environment environment = config.getEnv().get("QA");

		System.out.println(environment);
		return environment;

	}

}
