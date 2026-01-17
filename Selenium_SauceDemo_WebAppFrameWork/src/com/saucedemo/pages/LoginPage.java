package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;

    By UserNameField = By.id("user-name");
    By PasswordField = By.id("password");
    By LoginBtn = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void login(String user, String pass) {
        driver.findElement(UserNameField).sendKeys(user);
        driver.findElement(PasswordField).sendKeys(pass);
        driver.findElement(LoginBtn).click();
    }
}
