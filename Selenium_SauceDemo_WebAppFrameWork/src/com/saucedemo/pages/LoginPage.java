package com.saucedemo.pages;

import java.security.PublicKey;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.utils.ActionUtils;

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
        return ActionUtils.isDisplayed(driver, driver.findElement(LoginErrorMessage));
    }
    
    public String getLoginErrorMessage() {
    	return ActionUtils.getText(driver, driver.findElement(LoginErrorMessage));
    }
    public boolean getLoginPageTitledisplayed() {
    	return ActionUtils.isDisplayed(driver, driver.findElement(LoginPageTitle));
    }
    public String getLoginPageTitleText() {
    	return ActionUtils.getText(driver, driver.findElement(LoginPageTitle));
    }
    public boolean getUserNameFieldDisplayed() {
    	return ActionUtils.isDisplayed(driver, driver.findElement(UserNameField));
    }
    public boolean getUserNameFieldEnabled() {
    	return ActionUtils.isEnabled(driver, driver.findElement(UserNameField));
    }
    public String getUserNameFieldPlaceholder() {
    	return driver.findElement(UserNameField).getAttribute("placeholder");
    }
    public boolean getPassWordFieldDisplayed() {
    	return ActionUtils.isDisplayed(driver, driver.findElement(PasswordField));
    }
    public boolean getUserPassWordFieldEnabled() {
    	return ActionUtils.isEnabled(driver, driver.findElement(PasswordField));
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
