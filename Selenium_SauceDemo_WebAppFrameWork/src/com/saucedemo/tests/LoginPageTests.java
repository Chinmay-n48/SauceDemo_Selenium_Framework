package com.saucedemo.tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;

public class LoginPageTests extends BaseTest{       
	LoginPage login;

	    @BeforeMethod
	    public void setupPage() {
	        login = new LoginPage(driver);
	    }
	
	@Test
	public void TC_LoginPage_001() throws InterruptedException {
		login.login("standard_user","secret_sauce");
		Thread.sleep(3000);
	}
}
