package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataObjects.BillingAddress;
import org.selenium.pom.dataObjects.Product;
import org.selenium.pom.dataObjects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FirstTestCase extends BaseTest {

    public String searchText = "Blue";

//    @Test
    public void guestCheckOutWithDirectTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("BillingAddress.json",
                BillingAddress.class);
        Product product = new Product(1215);

        HomePage homePage = new HomePage(getDriver());
        homePage.loadUrl().getHeader().navigateToStoreUsingMenu();

        StorePage storePage = new StorePage(getDriver());
        storePage.searchProduct(searchText);
        Assert.assertEquals(storePage.getSearchHeader(), "Search results: “"+searchText+"”");
        storePage.getProductTile().clickAddToCart(product.getName()).clickViewCart();

        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.setBillingDetails(billingAddress).clickDirectBankTransfer().clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getSuccessMsg(), "Thank you. Your order has been received.");
    }

//    @Test
    public void loginAndCheckOutWithDirectTransfer() throws InterruptedException, IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("BillingAddress.json",
                BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getConfigLoader().getUsername(), ConfigLoader.getConfigLoader().getPassword());

        HomePage homePage = new HomePage(getDriver());
        homePage.loadUrl().getHeader().navigateToStoreUsingMenu();

        StorePage storePage = new StorePage(getDriver());
        storePage.searchProduct(searchText);
        Assert.assertEquals(storePage.getSearchHeader(), "Search results: “"+searchText+"”");
        storePage.getProductTile().clickAddToCart(product.getName()).clickViewCart();

        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.clickHereForLoginLnk();
        checkoutPage
                .loginFromCheckoutPage(user)
                .setBillingDetails(billingAddress).clickDirectBankTransfer().clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getSuccessMsg(), "Thank you. Your order has been received.");
    }
}
