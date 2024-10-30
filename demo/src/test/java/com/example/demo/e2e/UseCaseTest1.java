package com.example.demo.e2e;

import java.time.Duration;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.openqa.selenium.support.ui.Select;

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

        // Establece el nivel de zoom al 90%
        chromeOptions.addArguments("--force-device-scale-factor=0.95");

        this.driver = new ChromeDriver(chromeOptions);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Maximiza la ventana del navegador
        driver.manage().window().maximize();

    }

    // Inicio del test

    @Test
    public void SystemTest_useCase1_l() {

        // 1. Login del veterinario con intento fallido y luego exitoso
        loginVeterinario();

        // 2. Registro de un nuevo cliente con un intento fallido de registro y luego exitoso
        crearCliente();

        // 3. Registro de la mascota asociada al cliente
        crearMascota();

        // 4. Login del cliente para verificar la información de la mascota
        loginCliente();

    }

    public void loginCliente() {
        // Ingresar a login cliente
        driver.get(BASE_URL + "/login");

        // Login del cliente
        WebElement inputCedulaLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCedula")));
        inputCedulaLogin.sendKeys("121212");
        WebElement btnLoginCliente = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnLogin")));
        btnLoginCliente.click();

        WebElement h3NombreCliente = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pNombreCliente")));
        Assertions.assertThat(h3NombreCliente.getText()).isEqualTo("Yeimy Acosta");

        List<WebElement> listPets = driver.findElements(By.className("h3ListMascotas"));
        Assertions.assertThat(listPets.size()).isEqualTo(1);

    }

    private void crearMascota() {
        
        WebElement icono = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#aAgregarMascota i.fa-circle-plus")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", icono);

        // Clic en el enlace contenedor
        WebElement aAddMascota = driver.findElement(By.id("aAgregarMascota"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", aAddMascota);

        // Completar el formulario de registro de mascota
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1OperationMascota")));

        WebElement inputNombreMascota = driver.findElement(By.id("nombre"));
        WebElement inputEdad = driver.findElement(By.id("edad"));
        WebElement inputPeso = driver.findElement(By.id("peso"));
        WebElement inputSexo = driver.findElement(By.id("sexo"));
        WebElement inputRaza = driver.findElement(By.id("raza"));
        WebElement inputFotoMascota = driver.findElement(By.id("foto"));

        // Llenar los valores en los campos
        inputNombreMascota.sendKeys("Firulais");
        inputEdad.sendKeys("3"); // Ejemplo de edad
        inputPeso.sendKeys("5.5"); // Ejemplo de peso en kg
        inputSexo.sendKeys("Macho");
        inputRaza.sendKeys("Gato");
        inputFotoMascota.sendKeys("https://f.i.uol.com.br/fotografia/2021/05/17/162127484460a2b0dca4d14_1621274844_3x4_md.jpg");

        // Selección del valor de enfermedad en el menú desplegable
        Select selectEnfermedad = new Select(driver.findElement(By.id("floatingEnfermedad")));
        selectEnfermedad.selectByVisibleText("Gripe Felina"); // Reemplaza con el nombre exacto de la enfermedad deseada

        // Selección del valor de estado en el menú desplegable
        Select selectEstado = new Select(driver.findElement(By.id("floatingEstado")));
        selectEstado.selectByVisibleText("Enfermo"); // Reemplaza con la descripción del estado deseado

        // Click en el botón de submit
        WebElement btnSubmitPet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnSubmitMascota")));
        btnSubmitPet.submit();

        // Validación de la creación de la mascota
        WebElement h1NombreMascota = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1InfoMascota")));
        Assertions.assertThat(h1NombreMascota.getText()).isEqualTo("Información de Firulais");

    }

    private void crearCliente() {

        // Creación de un Cliente
        WebElement btnAddCliente = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnAgregarCliente")));
        btnAddCliente.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1OperationCliente")));

        // Llenado de los campos
        WebElement inputNombre = driver.findElement(By.id("nombre"));
        WebElement inputCedula = driver.findElement(By.id("cedula"));
        WebElement inputEmail = driver.findElement(By.id("correo"));
        WebElement inputTelefono = driver.findElement(By.id("numero"));
        WebElement inputDireccion = driver.findElement(By.id("direccion"));
        WebElement inputFoto = driver.findElement(By.id("foto"));

        inputNombre.sendKeys("Yeimy Acosta");
        inputCedula.sendKeys("121212");
        inputEmail.sendKeys("w1com"); // Email inválido
        inputTelefono.sendKeys("1233455");
        inputDireccion.sendKeys("Calle 1 # 2-3");
        inputFoto.sendKeys("https://media.istockphoto.com/id/1389348844/es/foto/foto-de-estudio-de-una-hermosa-joven-sonriendo-mientras-está-de-pie-sobre-un-fondo-gris.jpg?s=612x612&w=0&k=20&c=kUufmNoTnDcRbyeHhU1wRiip-fNjTWP9owjHf75frFQ=");

        WebElement btnSubmitCliente = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnSumitCliente")));
        Actions actions = new Actions(driver);
        actions.moveToElement(btnSubmitCliente).click().perform();

        // Corrección de datos y nuevo intento
        if (driver.findElement(By.id("correo")).getAttribute("class").contains("ng-invalid")) {
            driver.findElement(By.id("correo")).clear();
            driver.findElement(By.id("correo")).sendKeys("w@ex.com"); // Email válido
        }

        btnSubmitCliente.submit();

        // Verificación de creación exitosa
        WebElement h1NombreCliente = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1NombreCliente")));
        Assertions.assertThat(h1NombreCliente.getText()).isEqualTo("Información de Yeimy Acosta");

    }

    public void loginVeterinario() {

        driver.get(BASE_URL + "/login");

        // Selección de login para veterinario
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("aBtnChangeVet"))).click();

        // Primer intento de login fallido
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCorreo")));
        WebElement inputCorreo = driver.findElement(By.id("txtCorreo"));
        WebElement inputPassword = driver.findElement(By.id("txtPassword"));

        inputCorreo.sendKeys("s@t.com");
        inputPassword.sendKeys("1230");

        WebElement btnLogin = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLogin")));
        btnLogin.submit();

        // Manejo de la alerta de error
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept(); // Cerrar la alerta si aparece

        // Segundo intento de login
        inputCorreo.clear();
        inputPassword.clear();
        inputCorreo.sendKeys("s@t.com");
        inputPassword.sendKeys("1234");

        btnLogin.submit();

        // Ingreso de un veterinario
        WebElement h1Search = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1Search")));
        Assertions.assertThat(h1Search.getText()).isEqualTo("Buscar clientes");

    }

    @AfterEach
    public void close() {
        // driver.quit();
    }

}