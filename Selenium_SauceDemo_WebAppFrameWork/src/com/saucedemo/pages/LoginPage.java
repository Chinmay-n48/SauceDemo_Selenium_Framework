package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.saucedemo.base.BaseTest;

public class LoginPage{
	WebDriver driver;

    public By UserNameField = By.id("user-name");
    public By PasswordField = By.id("password");
    public By LoginBtn = By.id("login-button");
    public By LoginErrorMessage=By.className("error-message-container");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void login(String user, String pass) {
        driver.findElement(UserNameField).sendKeys(user);
        driver.findElement(PasswordField).sendKeys(pass);
        driver.findElement(LoginBtn).click();
    }
    public boolean getLoginErrorMessagedisplayed() {
    	return driver.findElement(LoginErrorMessage).isDisplayed();
    }
    public String getLoginErrorMessage() {
    	return driver.findElement(LoginErrorMessage).getText();
    }
}
