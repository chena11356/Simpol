package com.example.android.simpol;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
    public static void main(String[] args) throws Exception {
        WebDriver driver=new FirefoxDriver();
        driver.get("http://qaautomated.blogspot.in");
        Thread.sleep(3000);
        driver.quit();
    }
}
