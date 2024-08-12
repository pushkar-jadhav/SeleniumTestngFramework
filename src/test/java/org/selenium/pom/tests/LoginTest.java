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

public class LoginTest extends BaseTest {

    @Test(description = "Login during checkout")
    public void LoginDuringCheckout() throws IOException {
        User user = new User();
        String username = "demo"+new FakerUtils().generateRandomNumber();
        user.setUsername(username)
                .setPassword("Test123")
                .setEmail(username+"@example.com");
        SignUpApi signup = new SignUpApi();
        signup.registerUser(user);

        Product product = new Product(1215);
        CartApi cartApi = new CartApi();
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());

        checkoutPage.load()
                .clickHereForLoginLnk()
                .loginFromCheckoutPage(user);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }
}
