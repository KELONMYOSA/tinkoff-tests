package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/credentials.properties",
})
public interface ProjectConfig extends Config {

    //Web properties
    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("browserMobileView")
    @DefaultValue("")
    String browserMobileView();

    @Key("remoteDriverUrl")
    @DefaultValue("")
    String remoteDriverUrl();

    @Key("videoStorage")
    @DefaultValue("")
    String videoStorage();

    //Mobile properties
    @Key("deviceHost")
    String deviceHost();

    @Key("platformName")
    String platformName();

    @Key("deviceName")
    String deviceName();

    @Key("platformVersion")
    String platformVersion();

    @Key("browserstackUser")
    String browserstackUser();

    @Key("browserstackKey")
    String browserstackKey();

    @Key("browserstackAppUrl")
    String browserstackAppUrl();
}