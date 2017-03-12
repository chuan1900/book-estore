package mytestpack;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class login_test { 
 //To open Firefox browser
 WebDriver driver = new FirefoxDriver();
 
 @Before
 public void beforetest() {

 //To Maximize Browser Window
  driver.manage().window().maximize();

 //To Open URL In browser
  driver.get("http://localhost:8080/estore/jsps/user/login.jsp");
 }
 
 @After
 public void aftertest() {
  driver.quit();
  
 }
 
 @Test
 public void test() 
  {
   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  //Locating element by Name and type given texts in to input box.
   driver.findElement(By.name("username")).sendKeys("TestName");
   driver.findElement(By.name("password")).sendKeys("TestPass");
  }
 

 }