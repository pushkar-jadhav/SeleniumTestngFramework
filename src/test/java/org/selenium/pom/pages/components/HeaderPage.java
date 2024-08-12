package org.selenium.pom.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class HeaderPage extends BasePage {
    private final By storeMenuLnk = By.cssSelector("#menu-item-1227 > a");

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void navigateToStoreUsingMenu()
    {
        waitForElementToBeVisible(storeMenuLnk).click();
    }
}
