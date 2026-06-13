package com.ui.test;

import com.ui.pages.HomePage;

public class LoginTest3 {

	public static void main(String[] args) {
		
		HomePage homepage = new HomePage("chrome");
		String name = homepage.goToLoginPage().login("senax57658@sixoplus.com","9876543210").getUserName();
		System.out.println(name);
		
		homepage.quitDriver();
		}

}
