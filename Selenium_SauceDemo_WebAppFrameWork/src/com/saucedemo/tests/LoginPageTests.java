package com.saucedemo.tests;
import java.sql.Time;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;

public class LoginPageTests extends BaseTest {       
	LoginPage login;

	    @BeforeMethod
	    public void setupPage() {
	        login = new LoginPage(driver);
	    }
	
	@Test
	//Valid Login
	public void TC_LoginPage_001()  {
		login.login("standard_user","secret_sauce");
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),"Login failed for problem user");
		driver.findElement(By.xpath("//span[@class='title']")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='title']")).getText(), "Products" , "Product title is not displayed");
	}
	@Test
	//Login using Enter key
		public void TC_LoginPage_002() {
			driver.findElement(login.UserNameField).sendKeys("standard_user");
	        driver.findElement(login.PasswordField).sendKeys("secret_sauce");
	        driver.findElement(login.LoginBtn).sendKeys(Keys.ENTER);
			Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for problem user");
			Assert.assertEquals(driver.findElement(By.xpath("//span[@class='title']")).getText(), "Products" , "Product title is not displayed");
		}
	@Test
	//Invalid Username
		public void TC_LoginPage_003() {
		     login.login("standarduser","secret_sauce");
		     Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
		     Assert.assertEquals(login.getLoginErrorMessage(),"Epic sadface: Username and password do not match any user in this service", "Error message is not matched");
		}
	@Test
	//Invalid Password
	public void TC_LoginPage_004()  {
		login.login("standard_user","secretsauce");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
	     Assert.assertEquals(login.getLoginErrorMessage(),"Epic sadface: Username and password do not match any user in this service", "Error message is not matched");
	}
	@Test
	//Invalid Username and Password
	public void TC_LoginPage_005()  {
		login.login("standarduser","secretsauce");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
	     Assert.assertEquals(login.getLoginErrorMessage(),"Epic sadface: Username and password do not match any user in this service", "Error message is not matched");
	}
	@Test
	//Blank Username and Password
	public void TC_LoginPage_006()  {
		login.login("","");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
	     Assert.assertEquals(login.getLoginErrorMessage(),"Epic sadface: Username is required", "Error message is not matched");
	}
	@Test
	//Blank Username
	public void TC_LoginPage_007()  {
		login.login("","secret_sauce");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
		Assert.assertEquals(login.getLoginErrorMessage(), "Epic sadface: Username is required", "Error message is not matched");
	}
	@Test
	//Blank Password
	public void Tc_LoginPage_008() {
		login.login("standard_user", "");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
		Assert.assertEquals(login.getLoginErrorMessage(), "Epic sadface: Password is required", "Error message is not matched");		
	}
	@Test
	//Locked Out User
	public void Tc_LoginPage_009() {
		login.login("locked_out_user", "secret_sauce");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
		Assert.assertEquals(login.getLoginErrorMessage(), "Epic sadface: Sorry, this user has been locked out.","Error message is not matched");
	}
	@Test
	//Problem User Login
	public void Tc_LoginPage_010() {
		login.login("problem_user", "secret_sauce");
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for problem user");
		WebElement imageElement=driver.findElement(By.xpath("//img[@alt='Sauce Labs Backpack']"));
		Assert.assertEquals(imageElement.getAttribute("src"), "https://www.saucedemo.com/static/media/sl-404.168b1cce10384b857a6f.jpg", "The broken image is not displayed");
	}
	@Test
	//Performance Glitch User
	public void Tc_LoginPage_011() {
		long startTime  = System.nanoTime();
		login.login("performance_glitch_user", "secret_sauce");
		long endTime = System.nanoTime();
	    long durationNano = endTime - startTime;
	    long durationMillis = durationNano / 1_000_000;
	    Assert.assertTrue(durationMillis>5000,"Chinmay");;
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for problem user");				
	}
	
}
