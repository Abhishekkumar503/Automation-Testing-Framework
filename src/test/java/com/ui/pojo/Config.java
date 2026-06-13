package com.ui.pojo;

import java.util.Map;

public class Config {

//    private Environment DEV;
//    private Environment QA;
//    private Environment UAT;
//
//    public Environment getDEV() {
//        return DEV;
//    }
//
//    public Environment getQA() {
//        return QA;
//    }
//
//    public Environment getUAT() {
//        return UAT;
//    }
	
	private Map<String,Environment> env;

	public Map<String,Environment> getEnv() {
		return env;
	}

	public void setEnv(Map<String,Environment> env) {
		this.env = env;
	}
	
	
}