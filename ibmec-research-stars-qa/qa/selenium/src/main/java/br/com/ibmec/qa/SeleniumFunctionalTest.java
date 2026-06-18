package br.com.ibmec.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

public class SeleniumFunctionalTest {
    public static void main(String[] args) throws Exception {
        String seleniumUrl = env("SELENIUM_REMOTE_URL", "http://localhost:4444/wd/hub");
        String frontendUrl = env("FRONTEND_URL", "http://localhost:5173/login");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1366,768");

        RemoteWebDriver driver = new RemoteWebDriver(new URL(seleniumUrl), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        try {
            driver.get(frontendUrl);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")))
                    .sendKeys("admin@ibmec.br");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.xpath("//button[contains(., 'Entrar')]")).click();

            wait.until(ExpectedConditions.urlContains("/admin/dashboard"));
            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(., 'Dashboard de conformidade')]")
            ));

            if (!heading.getText().contains("Dashboard de conformidade")) {
                throw new AssertionError("Dashboard nao foi exibido apos login.");
            }

            System.out.println("Selenium WebDriver executado com sucesso.");
            System.out.println("Login admin validado no frontend.");
            System.out.println("URL final: " + driver.getCurrentUrl());
        } finally {
            driver.quit();
        }
    }

    private static String env(String name, String fallback) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? fallback : value;
    }
}
