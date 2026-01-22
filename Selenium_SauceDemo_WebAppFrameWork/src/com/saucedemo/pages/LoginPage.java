package com.saucedemo.pages;

import java.security.PublicKey;
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
    public By LoginPageTitle=By.xpath("//div [@class='login_logo']");
    public By ErrorMessageCloseBtn=By.xpath("//button[@class='error-button']");
    
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
    public boolean getLoginPageTitledisplayed() {
    	return driver.findElement(LoginPageTitle).isDisplayed();
    }
    public String getLoginPageTitleText() {
    	return driver.findElement(LoginPageTitle).getText();
    }
    public boolean getUserNameFieldDisplayed() {
    	return driver.findElement(UserNameField).isDisplayed();
    }
    public boolean getUserNameFieldEnabled() {
    	return driver.findElement(UserNameField).isEnabled();
    }
    public String getUserNameFieldPlaceholder() {
    	return driver.findElement(UserNameField).getAttribute("placeholder");
    }
    public boolean getPassWordFieldDisplayed() {
    	return driver.findElement(PasswordField).isDisplayed();
    }
    public boolean getUserPassWordFieldEnabled() {
    	return driver.findElement(PasswordField).isEnabled();
    }
    public String getPassWordFieldPlaceholder() {
    	return driver.findElement(PasswordField).getAttribute("placeholder");
    }
    public boolean getLoginButtonClickable() {
    	return driver.findElement(LoginBtn).isEnabled();
    }
    public String getLoginBtnText() {
    	return driver.findElement(LoginBtn).getAttribute("value");
    }
    public String getPasswordFieldMasking() {
    	return driver.findElement(PasswordField).getAttribute("Type");
    }
}
