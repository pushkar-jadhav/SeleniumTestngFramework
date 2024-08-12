package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.dataObjects.BillingAddress;
import org.selenium.pom.dataObjects.User;

public class CheckoutPage extends BasePage {
    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By companyFld = By.id("billing_company");
    private final By addressLine1Fld = By.id("billing_address_1");
    private final By addressLine2Fld = By.id("billing_address_2");
    private final By cityFld = By.id("billing_city");
    private final By postCodeFld = By.id("billing_postcode");
    private final By phoneFld = By.id("billing_phone");
    private final By emailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successMsg = By.cssSelector(".woocommerce-notice");
    private final By showLoginLnk = By.cssSelector(".showlogin");
    private final By usernameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.name("login");
    private final By overLay = By.cssSelector(".blockUI.blockOverlay");
    private final By countryDD = By.id("billing_country");
    private final By stateDD = By.id("billing_state");
    private final By directBankTransferRdBtn = By.id("payment_method_bacs");
    private final By productNameTxt = By.cssSelector("td.product-name");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load()
    {
        loadUrl("/checkout");
        return this;
    }

    @Step
    public CheckoutPage enterFirstName(String text)
    {
        String name= "pushkar";
        WebElement element = waitForElementToBeVisible(firstNameFld);
        element.clear();
        element.sendKeys(text);
        return this;
    }

    @Step
    public CheckoutPage enterLastName(String text)
    {
        WebElement element = waitForElementToBeVisible(lastNameFld);
        element.clear();
        element.sendKeys(text);
        return this;
    }
    @Step
    public CheckoutPage enterCompanyName(String text)
    {
        WebElement element = waitForElementToBeVisible(companyFld);
        element.clear();
        element.sendKeys(text);
        return this;
    }
    @Step
    public CheckoutPage selectCountry(String text)
    {
        WebElement element = waitForElementToBeVisible(countryDD);
        Select select = new Select(element);
        select.selectByVisibleText(text);
        return this;
    }
    @Step
    public CheckoutPage enterAddressLine1(String text)
    {
        WebElement element = waitForElementToBeVisible(addressLine1Fld);
        element.clear();
        element.sendKeys(text);
        return this;
    }
    @Step
    public CheckoutPage enterAddressLine2(String text)
    {
        WebElement element = waitForElementToBeVisible(addressLine2Fld);
        element.clear();
        element.sendKeys(text);
        return this;
    }
    @Step
    public CheckoutPage enterCity(String text)
    {
        WebElement element = waitForElementToBeVisible(cityFld);
        element.clear();
        element.sendKeys(text);
        return this;
    }
    @Step
    public CheckoutPage selectState(String text)
    {
        WebElement element = waitForElementToBeVisible(stateDD);
        Select select = new Select(element);
        select.selectByVisibleText(text);
        return this;
    }
    @Step
    public CheckoutPage enterPostalCode(String text)
    {
        WebElement element = waitForElementToBeVisible(postCodeFld);
        element.clear();
        element.sendKeys(text);
        return this;
    }
    @Step
    public CheckoutPage enterPhone(String text)
    {
        WebElement element = waitForElementToBeVisible(phoneFld);
        element.clear();
        element.sendKeys(text);
        return this;
    }
    @Step
    public CheckoutPage enterEmail(String text)
    {
        WebElement element = waitForElementToBeVisible(emailFld);
        element.clear();
        element.sendKeys(text);
        return this;
    }
    @Step
    public CheckoutPage clickDirectBankTransfer()
    {
        WebElement element = waitForElementToBeClickable(directBankTransferRdBtn);
        if(!element.isSelected())
        {
            element.click();
        }
        return this;
    }
    @Step
    public CheckoutPage clickPlaceOrder()
    {
        waitForOverlaystoDisappear(overLay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }
    @Step
    public String getSuccessMsg()
    {
        waitForOverlaystoDisappear(overLay);
        return waitForElementToBeVisible(successMsg).getText();
    }
    @Step
    public CheckoutPage clickHereForLoginLnk()
    {
        waitForElementToBeClickable(showLoginLnk).click();
        return this;
    }
    @Step
    public CheckoutPage enterUsername(String text)
    {
        waitForElementToBeVisible(usernameFld).sendKeys(text);
        return this;
    }
    @Step
    public CheckoutPage enterPassword(String text)
    {
        waitForElementToBeVisible(passwordFld).sendKeys(text);
        return this;
    }
    @Step
    public CheckoutPage clickLogin()
    {
        waitForElementToBeClickable(loginBtn).click();
        waitForElementToBeInVisible(loginBtn);
        return this;
    }
    @Step
    public CheckoutPage setBillingDetails(BillingAddress billingAddress)
    {
        return enterFirstName(billingAddress.getFirstName()).enterLastName(billingAddress.getLastName())
                .enterCompanyName(billingAddress.getCompanyName()).selectCountry(billingAddress.getCountry())
                .enterAddressLine1(billingAddress.getAddLine1()).enterAddressLine2(billingAddress.getAddLine2())
                .enterCity(billingAddress.getCity()).selectState(billingAddress.getState())
                .enterPostalCode(billingAddress.getPostalCode()).enterPhone(billingAddress.getPhone())
                .enterEmail(billingAddress.getEmail());
    }
    @Step
    public CheckoutPage loginFromCheckoutPage(User user)
    {
        enterUsername(user.getUsername()).enterPassword(user.getPassword()).clickLogin();
        return this;
    }
    @Step
    public String getProductName()
    {
        return waitForElementToBeVisible(productNameTxt).getText();
    }
}
