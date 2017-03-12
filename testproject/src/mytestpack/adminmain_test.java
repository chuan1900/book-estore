package mytestpack;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class adminmain_test { 
 //To open Firefox browser
 WebDriver driver = new FirefoxDriver();
 
 @Before
 public void beforetest() {

 //To Maximize Browser Window
  driver.manage().window().maximize();

 //To Open URL In browser
  driver.get("http://localhost:8080/estore/adminjsps/admin/main.jsp");
  
 }
 
 @After
 public void aftertest() {
  driver.quit();
  
 }
 
 @Test
 public void test() 
  {
   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
   String  datentime = driver.findElement(By.className("table")).getText();//Locating element by className and store its text to variable datentime.
   System.out.print(datentime);
  }
 
 }