package org.selenium.pom.apis.actions;

import org.selenium.pom.dataObjects.Product;
import org.selenium.pom.dataObjects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {

    public static void main(String args[])
    {
        User user = new User();
        String username = "demo"+new FakerUtils().generateRandomNumber();
        user.setUsername(username)
                .setPassword("Test123")
                .setEmail(username+"@example.com");
        SignUpApi signup = new SignUpApi();
        signup.registerUser(user);
        System.out.println("REGISTER COOKIES: "+signup.getCookies());

        Product product = new Product();
        product.setId(1215);
        CartApi cartApi = new CartApi(signup.getCookies());
        cartApi.addToCart(product.getId(), 1);
        System.out.println("CART PAGE COOKIES: "+cartApi.getCookies());
    }
}
