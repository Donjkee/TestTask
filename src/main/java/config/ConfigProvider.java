package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider
{
    public static final DriverConfigProps CONFIG_PROPS;

    static
    {
        CONFIG_PROPS = ConfigFactory.create(DriverConfigProps.class);
        if(CONFIG_PROPS == null)
        {
            throw new RuntimeException("Config file is not provided. Please find config file in test/java/resources/config");
        }
    }
}
