package org.selenium.pom.tests;

import org.selenium.pom.apis.actions.CartApi;
import org.selenium.pom.apis.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataObjects.BillingAddress;
import org.selenium.pom.dataObjects.Product;
import org.selenium.pom.dataObjects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    @Test(description = "Guest checkout using direct bank transfer")
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("BillingAddress.json",
                BillingAddress.class);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartapi = new CartApi();
        Product product = new Product(1215);
        cartapi.addToCart(product.getId(),1);
        injectCookiesToBrowser(cartapi.getCookies());
        checkoutPage.load();

        checkoutPage
                .setBillingDetails(billingAddress).clickDirectBankTransfer().clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getSuccessMsg(), "Thank you. Your order has been received.");
    }

    @Test(description = "Login and checkout using direct bank transfer")
    public void loginAndCheckoutUsingDirectBankTranfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("BillingAddress.json",
                BillingAddress.class);

        User user = new User();
        String username = "demo"+new FakerUtils().generateRandomNumber();
        user.setUsername(username)
                .setPassword("Test123")
                .setEmail(username+"@example.com");
        SignUpApi signup = new SignUpApi();
        signup.registerUser(user);

        Product product = new Product(1215);
        CartApi cartApi = new CartApi(signup.getCookies());
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(signup.getCookies());

        checkoutPage.load()
                .setBillingDetails(billingAddress).clickDirectBankTransfer().clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getSuccessMsg(), "Thank you. Your order has been received.");
    }
}
