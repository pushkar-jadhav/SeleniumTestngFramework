package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {
//    private final By productNamelbl = By.cssSelector("td[class='product-name'] a");
//    private final By checkOutBtn = By.cssSelector(".checkout-button");

    @FindBy(css="td[class='product-name'] a") private WebElement productNamelbl;
    @FindBy(how = How.CSS, using = ".checkout-button") private WebElement checkOutBtn;
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Step
    public String getProductName()
    {
        return waitForElementToBeVisible(productNamelbl).getText();
    }
    @Step
    public void clickCheckout()
    {
        waitForElementToBeClickable(checkOutBtn).click();
    }
}
