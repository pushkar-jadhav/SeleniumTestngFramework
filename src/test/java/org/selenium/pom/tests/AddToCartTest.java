package org.selenium.pom.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataObjects.Product;
import org.selenium.pom.dataProviders.DataProviders;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class AddToCartTest extends BaseTest {

    @Link(name = "allure", type="mylink")
    @TmsLink("JIRA-1234")
    @Issue("DEF-123456")
    @Description("This test case validates the add to cart feature from store page")
    @Test(description = "Add to cart from store page")
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        StorePage storePage = new StorePage(getDriver());
        storePage.load().
                getProductTile().
                clickAddToCart(product.getName()).clickViewCart();
        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = DataProviders.class, description = "Add To Cart featured products")
    public void addToCartFeaturedProducts(Product product){
        HomePage homePage = new HomePage(getDriver());
        homePage.loadUrl()
                .getProductTile()
                .clickAddToCart(product.getName())
                .clickViewCart();

        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }


}
