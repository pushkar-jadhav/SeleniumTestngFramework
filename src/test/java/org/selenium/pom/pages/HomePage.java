package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.HeaderPage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class HomePage extends BasePage {

    private HeaderPage header;
    private ProductThumbnail productTile;

    public HomePage(WebDriver driver) {
        super(driver);
        header = new HeaderPage(driver);
        productTile = new ProductThumbnail(driver);
    }
    @Step
    public HomePage loadUrl()
    {
        loadUrl("/");
        return this;
    }
    @Step
    public HeaderPage getHeader() {
        return header;
    }

    @Step
    public ProductThumbnail getProductTile() {
        return productTile;
    }

}
