package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage
{
    private final By searchFld = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By searchHeader = By.cssSelector(".woocommerce-products-header__title.page-title");

    private ProductThumbnail productTile;

    public StorePage(WebDriver driver) {
        super(driver);
        productTile = new ProductThumbnail(driver);
    }

    @Step
    public StorePage load()
    {
        loadUrl("/store");
        return this;
    }
    @Step
    private StorePage enterSearchText(String searchText)
    {
        waitForElementToBeVisible(searchFld).sendKeys(searchText);
        return this;
    }

    @Step
    private StorePage clickSearch()
    {
        waitForElementToBeClickable(searchBtn).click();
        return this;
    }

    @Step
    public StorePage searchProduct(String name)
    {
        enterSearchText(name).clickSearch();
        return this;
    }

    @Step
    public String getSearchHeader()
    {
        return waitForElementToBeVisible(searchHeader).getText();
    }

    @Step
    public ProductThumbnail getProductTile() {
        return productTile;
    }
}
