package org.selenium.pom.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.selenium.pom.dataObjects.BillingAddress;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

    public static <T> T deserializeJson(String fileName, Class<T> T) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream input = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        return mapper.readValue(input, T);
    }
}
