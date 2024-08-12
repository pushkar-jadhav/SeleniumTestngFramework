package org.selenium.pom.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class ProductThumbnail extends BasePage {
    private final By viewCartLnk = By.cssSelector("a[title='View cart']");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getAddToCartBtnElement(String productName)
    {
        return By.cssSelector("a[aria-label='Add “"+productName+"” to your cart']");
    }
    @Step
    public ProductThumbnail clickAddToCart(String productName)
    {
        By addToCartBtn = getAddToCartBtnElement(productName);
        waitForElementToBeClickable(addToCartBtn).click();
        return this;
    }
    @Step
    public void clickViewCart()
    {
        waitForElementToBeVisible(viewCartLnk).click();
    }
}
