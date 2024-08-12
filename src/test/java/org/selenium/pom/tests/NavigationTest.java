package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test(description = "Navigate from HOME to STORE using main menu")
    public void navigateFromHomeToStoreUsingMainMenu()
    {
        HomePage homePage = new HomePage(getDriver());
        homePage.loadUrl().getHeader().navigateToStoreUsingMenu();
        StorePage storePage = new StorePage(getDriver());
        Assert.assertEquals(storePage.getSearchHeader(), "Store");
    }
}
