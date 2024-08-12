package org.selenium.pom.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.selenium.pom.constants.DriverType;

public class DriverManagerOriginal {

    public WebDriver initializeDriver(String browser)
    {
        WebDriver driver;

        driver = switch (DriverType.valueOf(browser)) {
            case CHROME -> new ChromeDriver();
            case EDGE -> new EdgeDriver();
            default -> throw new IllegalStateException("Invalid browser name!!");
        };
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
