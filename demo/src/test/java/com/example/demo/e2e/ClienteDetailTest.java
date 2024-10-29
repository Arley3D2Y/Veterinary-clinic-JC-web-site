package com.example.demo.e2e;

import java.time.Duration;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

   private WebDriver driver;
   private WebDriverWait wait;

   @BeforeEach
   public void init(){

      WebDriverManager.chromedriver().setup();

      ChromeOptions chromeOptions = new ChromeOptions();

      chromeOptions.addArguments("--disable-notifications");
      chromeOptions.addArguments("--disable-extensions");

      this.driver = new ChromeDriver(chromeOptions);
      this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));

   }

   @Test
   public void SystemTest_clienteDashboard_ClienteName(){

      driver.get(BASE_URL + "/cliente/dashboard/1435466");

      String xpathNombreCliente = "/html/body/app-root//app-dashboard//div//header//app-cliente-info-card//form//label[1]//p";
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathNombreCliente)));
      WebElement pNombre = driver.findElement(By.xpath(xpathNombreCliente));

      String expected = "Juan Carlos";
      Assertions.assertThat(pNombre.getText()).isEqualTo(expected);
   }

   @Test
   public void SystemTest_clienteDetail_ClienteName2(){

      driver.get(BASE_URL + "/cliente/dashboard/1435466");

      wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pNombre")));
      WebElement pNombre = driver.findElement(By.id("pNombre"));

      String expected = "Juan Carlos";
      Assertions.assertThat(pNombre.getText()).isEqualTo(expected);
   }

   @Test
   public void SystemTest_clienteDashboard_petsSize(){
      
      driver.get(BASE_URL + "/cliente/dashboard/1435466");

      wait.until(ExpectedConditions.presenceOfElementLocated(By.className("card-pet")));
      List<WebElement> listPets = driver.findElements(By.className("pet-card"));

      Assertions.assertThat(listPets.size()).isEqualTo(2);
   }


}