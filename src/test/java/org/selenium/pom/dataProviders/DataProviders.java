package org.selenium.pom.dataProviders;

import org.selenium.pom.dataObjects.Product;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;

public class DataProviders {

    @DataProvider(name="getFeaturedProducts", parallel = true)
    public Object[] getFeaturedProducts() throws IOException {
        ArrayList<Product> list = new ArrayList<>();

        Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
        for(Product product:products)
        {
            if(product.isFeatured())
            {
                list.add(product);
            }
        }
        return list.toArray();
    }
}
