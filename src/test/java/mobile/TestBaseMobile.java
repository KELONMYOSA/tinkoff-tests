package mobile;

import com.codeborne.selenide.Configuration;
import drivers.BrowserstackSelenideDriver;
import drivers.EmulatorMobileDriver;
import helpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static config.Project.config;
import static helpers.WebDriverUtils.getSessionId;

public class TestBaseMobile {

    private static final String DEVICE_HOST = config.deviceHost();

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        if (!Objects.equals(DEVICE_HOST, "browserstack") & !Objects.equals(DEVICE_HOST, "local")) {
            throw new NullPointerException("deviceHost can't be null");
        }

        switch (DEVICE_HOST) {
            case "browserstack":
                Configuration.browser = BrowserstackSelenideDriver.class.getName();
                break;
            case "local":
                Configuration.browser = EmulatorMobileDriver.class.getName();
                break;
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSourceMobile();

        closeWebDriver();
        if (DEVICE_HOST.equals("browserstack")) {
            AllureAttachments.addVideo(sessionId);
        }
    }
}
