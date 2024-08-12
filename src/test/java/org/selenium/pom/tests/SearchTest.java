package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(description = "Search With Partial Match")
    public void searchWithPartialMatch()
    {
        String searchText = "Blue";
        StorePage storePage = new StorePage(getDriver());
        storePage.load().searchProduct(searchText);
        Assert.assertEquals(storePage.getSearchHeader(), "Search results: “"+searchText+"”");
    }
}
