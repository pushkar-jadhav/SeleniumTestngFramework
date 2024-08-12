package org.selenium.pom.utils;

import org.selenium.pom.constants.EnvironmentType;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader()
    {
        String env = System.getProperty("env", String.valueOf(EnvironmentType.STAGE));
        switch (EnvironmentType.valueOf(env))
        {
            case STAGE:properties = PropertyUtils.propertyLoader(System.getProperty("user.dir")+
                    "/src/test/resources/stage_config.properties");
            break;
            case UAT:properties = PropertyUtils.propertyLoader(System.getProperty("user.dir")+
                    "/src/test/resources/uat_config.properties");
            break;
            default:throw new RuntimeException("Invalid Environment Type"+env);
        }

    }

    public static ConfigLoader getConfigLoader()
    {
        if(configLoader==null)
        {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl()
    {
        String prop = properties.getProperty("baseUrl");
        if(prop!=null)
        {
            return prop;
        }
        else
            throw new RuntimeException("Base URL is not specified in stage_config.properties");
    }

    public String getUsername()
    {
        String prop = properties.getProperty("username");
        if(prop!=null)
        {
            return prop;
        }
        else
            throw new RuntimeException("Username is not specified in stage_config.properties");
    }

    public String getPassword()
    {
        String prop = properties.getProperty("password");
        if(prop!=null)
        {
            return prop;
        }
        else
            throw new RuntimeException("Password is not specified in stage_config.properties");
    }

    public Long getLargeWait()
    {
        Long prop = Long.parseLong(properties.getProperty("largewait"));
        if(prop!=null)
        {
            return prop;
        }
        else
            throw new RuntimeException("Large Wait time is not specified in stage_config.properties");
    }

    public Long getSmallWait()
    {
        Long prop = Long.parseLong(properties.getProperty("smallwait"));
        if(prop!=null)
        {
            return prop;
        }
        else
            throw new RuntimeException("Small Wait time is not specified in stage_config.properties");
    }
}
