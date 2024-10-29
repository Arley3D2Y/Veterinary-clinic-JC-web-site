package com.example.demo.e2e;

import java.time.Duration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
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
public class UseCaseTest1 {

    private final String BASE_URL = "http://localhost:4200";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void init() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-extensions");

        this.driver = new ChromeDriver(chromeOptions);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    public void SystemTest_useCase1_login() {

        driver.get(BASE_URL + "/login");

        // Ingreso de un Veterinario
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("aIngresarVeterinario")));
        WebElement btnIngresarVeterinario = driver.findElement(By.id("aIngresarVeterinario"));
        btnIngresarVeterinario.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCorreo")));
        WebElement inputCorreo = driver.findElement(By.id("txtCorreo"));
        WebElement inputPassword = driver.findElement(By.id("txtPassword"));

        inputCorreo.sendKeys("s@t.com");
        inputPassword.sendKeys("1230");

        WebElement btnLogin = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLogin")));
        btnLogin.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept(); // Cerrar la alerta si aparece

        inputCorreo.clear();
        inputPassword.clear();
        inputCorreo.sendKeys("s@t.com");
        inputPassword.sendKeys("1234");

        btnLogin.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1Search")));
        WebElement h1Search = driver.findElement(By.id("h1Search"));
        String expected = "Buscar clientes";
        Assertions.assertThat(h1Search.getText()).isEqualTo(expected);

        // Creación de un Cliente
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("agregarCliente")));
        WebElement btnAddCliente = driver.findElement(By.id("agregarCliente"));
        btnAddCliente.click();
  
        // Cambiar la verificación
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
        expected = "Información de Yeimy Acosta";
        Assertions.assertThat(h1NombreCliente.getText()).isEqualTo(expected);
  

    }

    @AfterEach
    public void close() {
        // driver.quit();
    }

}