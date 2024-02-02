package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/driver.properties"})

public interface DriverConfigProps extends Config
{
    @Key("webdriver.path")
    String webdriverPath();

    @Key("web.url")
    String webUrl();

    @Key("driver.timeout")
    String driverTimeout();
}
