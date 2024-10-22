package com.example.demo.e2e;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.test.annotation.DirtiesContext; 
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) 
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) 
public class ClienteDetailTest {

    private final String BASE_URL = "http://localhost:4200";

   private WebDriver driver:
   private WebDriverwait wait;

@BeforeEach
public void init(){

   WebDriverManager.chromedriver().setup();

   ChromeOptions chromeOptions = new ChromeOptions();

   chromeOptions.addArguments("--disable-notifications"); 
   chromeOptions.addArguments("--disable-extensions"); 

   this.driver = new ChromeDriver (chromeOptions);
   this.wait new WebDriverWait(driver, Duration.ofSeconds(seconds:5));
}

@Test
public void SystemTest_clienteDetail_ClienteName(){

   driver.get(BASE_URL + "/cliente/detail/1");

   String pathNombre = "//*[@id=\"content\"]//app-cliente-detail//div//ul[1]//li[1]";

   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pathNombre)));
   WebElement liNombre = driver.findElement(By.xpath(pathNombre));

   String expectedName="Nombre: Juan Carlos";
   Assertions.assertThat(1iNombre.getText()).isEqualTo(expectedName);

}

@Test
public void SystemTest_clienteDetail_ClienteName2(){

   driver.get(BASE_URL + "/cliente/detail/1");

   wait.until(ExpectedConditions.presenceOfElementLocated(By.id("liNombre")));
   WebElement liNombre = driver.findElement(By.id("liNombre"));

   String expectedName="Nombre: Juan Carlos";
   Assertions.assertThat(1iNombre.getText()).isEqualTo(expectedName);

}

@Test
public void SystemTest_clienteDetail_ClienteName2(){

   driver.get(BASE_URL + "/cliente/detail/1");

   wait.until(ExpectedConditions.presenceOfElementLocated(By.id("liNombre")));
   WebElement liNombre = driver.findElement(By.id("liNombre"));

   String expectedName="Nombre: Juan Carlos";
   Assertions.assertThat(1iNombre.getText()).isEqualTo(expectedName);

}

@AfterEach
void tearDown(){

   driver.quit();
}
}
