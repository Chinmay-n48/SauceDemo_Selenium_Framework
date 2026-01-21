package com.saucedemo.tests;
import java.sql.Time;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
	//Verify login with valid credentials
	public void TC_LoginPage_001()  {
		login.login("standard_user","secret_sauce");
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),"Login failed for problem user");
		driver.findElement(By.xpath("//span[@class='title']")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='title']")).getText(), "Products" , "Product title is not displayed");
	}
	@Test
	//Verify login via keyboard
		public void TC_LoginPage_002() {
			driver.findElement(login.UserNameField).sendKeys("standard_user");
	        driver.findElement(login.PasswordField).sendKeys("secret_sauce");
	        driver.findElement(login.LoginBtn).sendKeys(Keys.ENTER);
			Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for problem user");
			Assert.assertEquals(driver.findElement(By.xpath("//span[@class='title']")).getText(), "Products" , "Product title is not displayed");
		}
	@Test
	//Verify error for invalid username
		public void TC_LoginPage_003() {
		     login.login("standarduser","secret_sauce");
		     Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
		     Assert.assertEquals(login.getLoginErrorMessage(),"Epic sadface: Username and password do not match any user in this service", "Error message is not matched");
		}
	@Test
	//Verify error for invalid password
	public void TC_LoginPage_004()  {
		login.login("standard_user","secretsauce");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
	     Assert.assertEquals(login.getLoginErrorMessage(),"Epic sadface: Username and password do not match any user in this service", "Error message is not matched");
	}
	@Test
	//Verify error for invalid credentials
	public void TC_LoginPage_005()  {
		login.login("standarduser","secretsauce");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
	     Assert.assertEquals(login.getLoginErrorMessage(),"Epic sadface: Username and password do not match any user in this service", "Error message is not matched");
	}
	@Test
	//Verify validation for blank fields
	public void TC_LoginPage_006()  {
		login.login("","");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
	     Assert.assertEquals(login.getLoginErrorMessage(),"Epic sadface: Username is required", "Error message is not matched");
	}
	@Test
	//Verify username mandatory validation
	public void TC_LoginPage_007()  {
		login.login("","secret_sauce");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
		Assert.assertEquals(login.getLoginErrorMessage(), "Epic sadface: Username is required", "Error message is not matched");
	}
	@Test
	//Verify password mandatory validation
	public void TC_LoginPage_008() {
		login.login("standard_user", "");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
		Assert.assertEquals(login.getLoginErrorMessage(), "Epic sadface: Password is required", "Error message is not matched");		
	}
	@Test
	//Verify locked user restriction
	public void TC_LoginPage_009() {
		login.login("locked_out_user", "secret_sauce");
		Assert.assertEquals(login.getLoginErrorMessagedisplayed(), true, "Error Message not displayed");
		Assert.assertEquals(login.getLoginErrorMessage(), "Epic sadface: Sorry, this user has been locked out.","Error message is not matched");
	}
	@Test
	//Verify problem user login
	public void TC_LoginPage_010() {
		login.login("problem_user", "secret_sauce");
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for problem user");
		WebElement imageElement=driver.findElement(By.xpath("//img[@alt='Sauce Labs Backpack']"));
		Assert.assertEquals(imageElement.getAttribute("src"), "https://www.saucedemo.com/static/media/sl-404.168b1cce10384b857a6f.jpg", "The broken image is not displayed");
	}
	@Test
	//Verify delayed login
	public void TC_LoginPage_011() {
		long startTime  = System.nanoTime();
		login.login("performance_glitch_user", "secret_sauce");
		long endTime = System.nanoTime();
	    long durationNano = endTime - startTime;
	    long durationMillis = durationNano / 1_000_000;
	    Assert.assertTrue(durationMillis>5000,"Login in the lessa than 5 sec ");;
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for user");				
	}
	@Test
	//Verify login page UI
	public void TC_Login_012() {
		Assert.assertTrue(login.getLoginPageTitledisplayed(), "Login Page title not displayed");
		Assert.assertEquals(login.getLoginPageTitleText(), "Swag Labs", "Login page title not matched");
		
		Assert.assertTrue(login.getUserNameFieldDisplayed(),"User name field is not displayed");
		Assert.assertTrue(login.getUserNameFieldEnabled(),"User name field is not enabled");
		Assert.assertEquals(login.getUserNameFieldPlaceholder(), "Username", "Placeholder is not matched");
		
		Assert.assertTrue(login.getPassWordFieldDisplayed(),"User name field is not displayed");
		Assert.assertTrue(login.getUserPassWordFieldEnabled(),"User name field is not enabled");
		Assert.assertEquals(login.getPassWordFieldPlaceholder(), "Password", "Placeholder is not matched");
		
		Assert.assertTrue(login.getLoginButtonClickable(), "Login button is not enabled");
		Assert.assertEquals(login.getLoginBtnText(), "Login", "Login button text is not matched");
		
	}
	@Test
	//Verify password masking
	public void TC_Login_013() {
		driver.findElement(login.PasswordField).sendKeys("secret_sauce");
		Assert.assertEquals(login.getPasswordFieldMasking(), "password", "Password not Masking");
	}
	@Test
	//Verify error close icon
	public void TC_Login_014() {
		login.login("standarduser", "secretsauce");
		Assert.assertTrue(login.getLoginErrorMessagedisplayed(), "Error Message not displayed");
		driver.findElement(login.ErrorMessageCloseBtn).click();
		
	}
	@Test
	//Verify error on retry
	public void TC_Login_015() {
		login.login("standarduser", "secretsauce");
		Assert.assertTrue(login.getLoginErrorMessagedisplayed(), "Error Message not displayed");
		driver.findElement(login.LoginBtn).click();
		Assert.assertTrue(login.getLoginErrorMessagedisplayed(), "Error Message not displayed");
	}
	@Test
	//Verify session on refresh
	public void TC_Login_016() {
		login.login("standard_user", "secret_sauce");
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for user");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='title']")).getText(), "Products" , "Product title is not displayed");
		driver.navigate().refresh();
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for user");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='title']")).getText(), "Products" , "Product title is not displayed");		
	}
	@Test
	//Verify session security
	public void TC_Login_017() {
		login.login("standard_user", "secret_sauce");
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed for user");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='title']")).getText(), "Products" , "Product title is not displayed");
		driver.navigate().back();
		Assert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/"),"User was not redirected to login page");
		Assert.assertTrue(login.getLoginPageTitledisplayed(), "Login Page title not displayed");
	}
	@Test
	//Verify access without login
	public void TC_Login_018() throws InterruptedException {
		driver.get("https://www.saucedemo.com/inventory.html");
		Thread.sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/"), "User was not redirected to login page");
		Assert.assertTrue(login.getLoginErrorMessagedisplayed(),"Error message not displayed");
		Assert.assertEquals(login.getLoginErrorMessage(),"Epic sadface: You can only access '/inventory.html' when you are logged in.","Error Message not displayed");
	}
	@Test
	//Verify login on multiple browsers
	public void TC_Login_019() {
		login.login("standard_user", "secret_sauce");
		
		// Desktop
	    driver.manage().window().setSize(new Dimension(1920, 1080));
	    Assert.assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());

	    // Tablet
	    driver.manage().window().setSize(new Dimension(768, 1024));
	    Assert.assertTrue(driver.findElement(By.id("react-burger-menu-btn")).isDisplayed());
	    
	 // Mobile
	    driver.manage().window().setSize(new Dimension(375, 667));
	    driver.findElement(By.id("react-burger-menu-btn")).click();		
	}
}
