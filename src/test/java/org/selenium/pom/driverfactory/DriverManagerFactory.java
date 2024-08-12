package org.selenium.pom.driverfactory;

import org.selenium.pom.constants.DriverType;

import java.sql.Driver;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType driverType)
    {
        switch (driverType)
        {
            case CHROME: return new ChromeDriverManager();
            case EDGE: return new EdgeDriverManager();
            default: throw new IllegalStateException("Invalid Driver Type"+driverType);
        }
    }
}
