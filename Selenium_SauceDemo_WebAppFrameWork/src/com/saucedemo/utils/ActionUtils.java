package com.saucedemo.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionUtils {
	WebDriver driver;
	
	public static void click(WebDriver driver, WebElement element) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.click();
	    } catch (Exception e) {
	        System.out.println("Click failed: " + e.getMessage());
	    }
	}
	
	public static boolean isEnabled(WebDriver driver, WebElement element) {
	    try {
	        return element.isEnabled();
	    } 
	    catch (Exception e) {
	        return false;
	    }
	}
	
	public static boolean isDisplayed(WebDriver driver, WebElement element) {
	    try {
	        return element.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public static String getText(WebDriver driver, WebElement element) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(element));

	        String text = element.getText();

	        if (text == null || text.isEmpty()) {
	            text = element.getAttribute("innerText");
	        }

	        return text.trim();

	    } catch (Exception e) {
	        System.out.println("Unable to get text: " + e.getMessage());
	        return "Null";
	    }
	}
		
	}
