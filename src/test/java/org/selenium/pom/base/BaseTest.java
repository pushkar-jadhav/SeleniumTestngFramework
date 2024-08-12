package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.driverfactory.DriverManagerFactory;
import org.selenium.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver)
    {
        this.driver.set(driver);
    }

    protected WebDriver getDriver()
    {
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void setUp(@Optional String browserName)
    {
        String browser = System.getProperty("browser", browserName);
//        String browser = "CHROME";
//        setDriver(new DriverManagerOriginal().initializeDriver(browser));
        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
        System.out.println("Current Thread: "+Thread.currentThread().getId()+" , Driver : "+getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public synchronized void tearDown(@Optional String browser, ITestResult result) throws IOException {
        System.out.println("Current Thread: "+Thread.currentThread().getId()+" , Driver : "+getDriver());
        if(result.getStatus()==ITestResult.FAILURE)
        {
            File destFile = new File("screenshots"+File.separator+browser+File.separator+
                    result.getTestClass().getRealClass().getSimpleName()+"_"+
                    result.getMethod().getMethodName()+".png");
//            takeScreenshot(destFile);
            takeFullPageScreenshot(destFile);
        }
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies)
    {
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookieToSeleniumCookie(cookies);
        for(Cookie cookie:seleniumCookies)
        {
            getDriver().manage().addCookie(cookie);
        }
    }

    private void takeScreenshot(File destFile) throws IOException {
        File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }

    private void takeFullPageScreenshot(File destFile) throws IOException {
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getDriver());

        try{
            ImageIO.write(screenshot.getImage(),"PNG", destFile);
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
