package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;

    private WebDriverWait wait;
    public BasePage(WebDriver driver)
    {
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigLoader.getConfigLoader().getLargeWait()));
    }

    public void loadUrl(String endpoint)
    {
        driver.get(ConfigLoader.getConfigLoader().getBaseUrl() +endpoint);
    }

    public void waitForOverlaystoDisappear(By element)
    {
        List<WebElement> overlays = driver.findElements(element);
        if(overlays.size()>0)
        {
            wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("Overlay disappears");
        }
        else
            System.out.println("No Overlay displayed");
    }

    public WebElement waitForElementToBeVisible(By element)
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public WebElement waitForElementToBeClickable(By element)
    {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public WebElement waitForElementToBeVisible(WebElement element)
    {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element)
    {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForElementToBeInVisible(By element)
    {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public boolean waitForElementToBeInVisible(WebElement element)
    {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
