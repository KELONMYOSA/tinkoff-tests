package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static config.Project.config;

public class BrowserstackSelenideDriver implements WebDriverProvider {

    public static URL getBrowserstackUrl() {
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(Capabilities caps) {

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(caps);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", config.browserstackUser());
        mutableCapabilities.setCapability("browserstack.key", config.browserstackKey());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", config.browserstackAppUrl());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", "Samsung Galaxy S22 Ultra");
        mutableCapabilities.setCapability("os_version", "12.0");

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", "Browserstack Mobile Test");
        mutableCapabilities.setCapability("build", "browserstack-build");
        mutableCapabilities.setCapability("name", "wikipedia-android-test");


        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }
}
