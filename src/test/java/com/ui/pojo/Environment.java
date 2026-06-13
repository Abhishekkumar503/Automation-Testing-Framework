package com.ui.pojo;

public class Environment {
	
	Environment(){}
	
	private String url; // make sure this variable should not class variable ( non static )

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
