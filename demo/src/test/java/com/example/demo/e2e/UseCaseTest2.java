package com.example.demo.e2e;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class UseCaseTest2 {

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

    @Test
    public void SystemTest_useCase2_l() {

        List<Integer> preData = new ArrayList<>();

        loginAdministrador();

        preData = getDataDashboard();

        loginVeterinario();

        buscarMascotas();

        loginAdministrador();

        List<Integer> postData = getDataDashboard();

        // Verifica que el primer valor en postData es igual a preData + 1
        Assertions.assertThat(postData.get(0)).isEqualTo(preData.get(0) + 1);

        // Verifica que postData[1] es mayor que preData[1]
        Assertions.assertThat(postData.get(1)).isGreaterThan(preData.get(1));

    }

    public void buscarMascotas() {

        List<WebElement> listNavLinks = driver.findElements(By.className("aNavLink"));
        listNavLinks.get(1).click();

        // Buscar mascota
        WebElement h1Search = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1Search")));
        Assertions.assertThat(h1Search.getText()).isEqualTo("Buscar mascotas");

        // Realizar búsqueda y hacer clic en el botón de búsqueda
        WebElement inputSearch = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputSearchBar")));
        inputSearch.sendKeys("Orion");
    
        WebElement btnSearch = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSearchBar")));
        btnSearch.click();

        // Esperar a que los elementos de la lista de resultados estén visibles
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("btn-dark")));

        // Volver a obtener la lista de elementos después de la actualización
        List<WebElement> listMascotas = driver.findElements(By.className("btn-dark"));
        WebElement firElementInfo = listMascotas.get(0);
        firElementInfo.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("h1InfoMascota")));

        // Hacer scroll hasta el final de la página
        // ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Clic en el enlace contenedor
        WebElement btnAgregarTratamiento = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAgregarTratamiento")));
        btnAgregarTratamiento.click();

        // Completar el formulario de registro de tratamiento
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1OperationTratamiento")));

        WebElement inputDescripcion = driver.findElement(By.id("descripcion"));
        WebElement inputObservaciones = driver.findElement(By.id("observaciones"));
        WebElement inputFechaInicip = driver.findElement(By.id("fechaInicio"));
        WebElement inputFechaFin = driver.findElement(By.id("fechaFin"));
        WebElement inputDroga = driver.findElement(By.id("droga"));

        inputDescripcion.sendKeys("Tratamiento de prueba");
        inputObservaciones.sendKeys("Observaciones de prueba");
        inputFechaInicip.sendKeys("02-10-2024"); // Fecha invalida2022-01-01");
        inputFechaFin.sendKeys("02-10-2024"); // Fecha invalida2022-01-01");
        inputDroga.sendKeys("ACOLAN");

        // Clic en el enlace contenedor
        WebElement btnSubmitTratamiento = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSubmitTratamiento")));
        btnSubmitTratamiento.submit();

        // Validación de la creación del tratamiento
        WebElement h1NombreMascota = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1InfoMascota")));
        Assertions.assertThat(h1NombreMascota.getText()).isEqualTo("Información de Orion");
        // Hacer scroll hasta el final de la página
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        List<WebElement> listTratamientos = driver.findElements(By.className("tdTratDescrip"));
        WebElement ultimoElementInfo = listTratamientos.get(listTratamientos.size() - 1);
        
        Assertions.assertThat(ultimoElementInfo.getText()).isEqualTo("Tratamiento de prueba");
        

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
        inputPassword.sendKeys("1234");

        WebElement btnLogin = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLogin")));
        btnLogin.submit();

        // Ingreso de un veterinario
        WebElement h1Search = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1Search")));
        Assertions.assertThat(h1Search.getText()).isEqualTo("Buscar clientes");

    }

    public List<Integer> getDataDashboard() {

        List<Integer> data = new ArrayList<>();

        List<WebElement> listNavLinks = driver.findElements(By.className("aNavLink"));
        listNavLinks.get(2).click();

        WebElement btnDashboard = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1DashboardAdmin")));
        Assertions.assertThat(btnDashboard.getText()).isEqualTo("Dashboard Administrador - Veterinario");

        WebElement strongCountTratamientos = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("strongCountTratamientos")));
        data.add(Integer.parseInt(strongCountTratamientos.getText()));

        // Desplázate al siguiente elemento "strongTotalProfits" si es necesario
        WebElement strongTotalProfits = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("strongTotalProfits")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", strongTotalProfits);
        data.add(Integer.parseInt(strongTotalProfits.getText()));

        return data;
    }

    public void loginAdministrador() {
        // Ingresar a login cliente
        driver.get(BASE_URL + "/login/administrador");

        // Login del cliente
        WebElement inputUsuario = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtUsuario")));
        inputUsuario.sendKeys("Arley");
        WebElement inputPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtPassword")));
        inputPassword.sendKeys("1111");
        WebElement btnLoginVeterinario = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnLogin")));
        btnLoginVeterinario.click();

        WebElement h1SearchVeterinarios = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("h1Search")));
        Assertions.assertThat(h1SearchVeterinarios.getText()).isEqualTo("Buscar veterinarios");

    }

    @AfterEach
    public void close() {
        // driver.quit();
    }

}