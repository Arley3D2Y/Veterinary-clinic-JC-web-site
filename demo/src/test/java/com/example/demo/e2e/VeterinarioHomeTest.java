package com.example.demo.e2e;

import java.time.Duration;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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
public class VeterinarioHomeTest {

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
   public void Clientes_deleteCliente_listSize() {

      driver.get(BASE_URL + "/veterinario/clientes");
      
      wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-danger")));
      // Obtener la lista inicial de elementos con la clase 'btn-danger'
      List<WebElement> list1 = driver.findElements(By.className("btn-danger"));
      int initialSize = list1.size();

      // Hacer clic en el primer botón para eliminar
      WebElement btnElement = list1.get(0); // Selecciona el primer botón en la lista
      btnElement.click();

      // Espera hasta que la lista de elementos 'btn-danger' se actualice
      wait.until(ExpectedConditions.numberOfElementsToBe(By.className("btn-danger"), initialSize - 1));

      // Obtener la lista de elementos después de la eliminación
      List<WebElement> list2 = driver.findElements(By.className("btn-danger"));
      // Verificar que el tamaño de la lista es el inicial menos 1
      Assertions.assertThat(list2.size()).isEqualTo(initialSize - 1);

   }

   @Test
   public void Clientes_addCliente_ClienteName() {

      driver.get(BASE_URL + "/veterinario/clientes");

      wait.until(ExpectedConditions.presenceOfElementLocated(By.id("agregarCliente")));
      WebElement btnElement = driver.findElement(By.id("agregarCliente"));
      btnElement.click();

      wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nombre")));

      WebElement inputNombre = driver.findElement(By.id("nombre"));
      WebElement inputCedula = driver.findElement(By.id("cedula"));
      WebElement inputEmail = driver.findElement(By.id("correo"));
      WebElement inputTelefono = driver.findElement(By.id("numero"));
      WebElement inputDireccion = driver.findElement(By.id("direccion"));
      WebElement inputFoto = driver.findElement(By.id("foto"));

      inputNombre.sendKeys("Yeimy Acosta");
      inputCedula.sendKeys("121212");
      inputEmail.sendKeys("w@ex.com");
      inputTelefono.sendKeys("1233455");
      inputDireccion.sendKeys("Calle 1 # 2-3");
      inputFoto.sendKeys("https://media.istockphoto.com/id/1389348844/es/foto/foto-de-estudio-de-una-hermosa-joven-sonriendo-mientras-está-de-pie-sobre-un-fondo-gris.jpg?s=612x612&w=0&k=20&c=kUufmNoTnDcRbyeHhU1wRiip-fNjTWP9owjHf75frFQ=");
      
      WebElement btnSumit = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSumitCliente")));
      btnSumit.click();

      wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1NombreCliente")));
      WebElement h1NombreCliente = driver.findElement(By.id("h1NombreCliente"));
      String expected = "Información de Yeimy Acosta";
      Assertions.assertThat(h1NombreCliente.getText()).isEqualTo(expected);
   }


   @AfterEach
   public void close(){
      // driver.quit();
   }


}