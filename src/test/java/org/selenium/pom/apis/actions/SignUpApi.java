package org.selenium.pom.apis.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.dataObjects.User;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class SignUpApi {

    private Cookies cookies;

    public Cookies getCookies()
    {
        return  cookies;
    }

    private String getRegisterFormNonceValueUsingGroovy()
    {
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll { it.@name =='woocommerce-register-nonce' }.@value");
    }

    private String getRegisterFormNonceValueUsingJsoup()
    {
        Response response = getAccount();
        Document document = Jsoup.parse(response.body().prettyPrint());
        Element element = document.getElementById("woocommerce-register-nonce");
        return element.attr("value");
    }
    private Response getAccount()
    {
        Cookies cookies = new Cookies();
        Response response =
                given().
                        baseUri(ConfigLoader.getConfigLoader().getBaseUrl()).
                        cookies(cookies).log().all().
                        when().
                        get("/account").
                        then().
//                        log().all().
                        extract().response();

        if(response.getStatusCode()!=200)
        {
            throw  new RuntimeException("Failed to fetch account, HTTP status code: "+response.getStatusCode());
        }
        return response;
    }

    public Response registerUser(User user)
    {
        Cookies cookies = new Cookies();

        Header header = new Header("Content-Type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String,String> formParams = new HashMap<>();
        formParams.put("username",user.getUsername());
        formParams.put("password",user.getPassword());
        formParams.put("email",user.getEmail());
        formParams.put("woocommerce-register-nonce",getRegisterFormNonceValueUsingJsoup());
        formParams.put("register","Register");

        Response response = given().
                baseUri(ConfigLoader.getConfigLoader().getBaseUrl()).
                headers(headers).
                cookies(cookies).
                formParams(formParams).
//                log().all().
                when().
                post("/account").
                then().
//                log().all().
                extract().response();

        if(response.statusCode()!=302)
        {
            throw new RuntimeException("Failed to register account, HTTP status code: "+response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
